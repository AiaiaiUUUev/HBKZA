package com.example.d.healthbook.Fragments;

/**
 * Created by D on 07.06.2017.
 */

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.d.healthbook.Activities.MainPageActivity;
import com.example.d.healthbook.Adapters.RecyclerСlinicListAdapter;
import com.example.d.healthbook.Appi.App;
import com.example.d.healthbook.GlobalVariables.GlobalVariables;
import com.example.d.healthbook.Models.ResponseClinicList;
import com.example.d.healthbook.R;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class See_Clinic_List_Fragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    Toolbar toolbar;


    int pastVisiblesItems, visibleItemCount, totalItemCount;
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private LinearLayoutManager mLayoutManager;
    private boolean loading = true;
    private int pages = 0;
    private RecyclerСlinicListAdapter recyclerСlinicListAdapter;
    private ResponseClinicList registeredUser;
    private String titleClinic_key;
    private String cityName_key;
    private String speciality_key;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setHasOptionsMenu(true);
            seeClinicList();


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //App.getInstance().setCurrentFragment(this);
        setHasOptionsMenu(true);

        return inflater.inflate(R.layout.see_clinic_list_fragment, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ((MainPageActivity) getActivity()).changeMenuItems(0);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.cliniclist_recycler);
        mLayoutManager = new LinearLayoutManager(getActivity());


        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container_clinic_list);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

    }

    int CURRENT_PAGE_NUM = 1;

    public void addNewDataintoRecycler() {

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) //check for scroll down
                {
                    visibleItemCount = mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();
                    if (!loading) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            if (CURRENT_PAGE_NUM >= 1 && CURRENT_PAGE_NUM < pages) {
                                loading = true;
                                Toast.makeText(getActivity(), "Загрузка списка клиник", Toast.LENGTH_SHORT).show();
                                addNewsList(CURRENT_PAGE_NUM + 1);
                            }
                        }
                    }
                }
            }
        });

    }

    public void addNewsList(Integer page_num) {
        App.getApi().addClinicList(page_num).enqueue(new Callback<ResponseClinicList>() {
            @Override
            public void onResponse(Call<ResponseClinicList> call, Response<ResponseClinicList> response) {
                int s = response.code();
                if (response.errorBody() != null) {
                    try {
                        Log.e("LOG", "Retrofit Response: " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                registeredUser = response.body();
                if (registeredUser.getDocuments() != null) {
                    loading = false;
                    CURRENT_PAGE_NUM++;
                    Toast.makeText(getActivity(), "Загрузка прошла успешно ", Toast.LENGTH_SHORT).show();

                    for (int i = 0; i < registeredUser.getDocuments().size(); i++) {
                        GlobalVariables.documentClinicList.add(registeredUser.getDocuments().get(i));
                    }

                    recyclerСlinicListAdapter.notifyDataSetChanged();


                } else {
                    Toast.makeText(getActivity(), "Ошибочка", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseClinicList> call, Throwable t) {
                Toast.makeText(getActivity(), "An error occurred during networking", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void seeClinicList() {
        App.getApi().seeClinicList().enqueue(new Callback<ResponseClinicList>() {
            @Override
            public void onResponse(Call<ResponseClinicList> call, Response<ResponseClinicList> response) {
                int s = response.code();
                if (response.errorBody() != null) {
                    try {
                        Log.e("LOG", "Retrofit Response: " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                registeredUser = response.body();
                if (registeredUser.getDocuments() != null) {
                    CURRENT_PAGE_NUM = 1;
                    loading = false;


                    GlobalVariables.documentClinicList = registeredUser.getDocuments();
                    recyclerСlinicListAdapter = new RecyclerСlinicListAdapter(GlobalVariables.documentClinicList, getActivity());

                    mRecyclerView.setLayoutManager(mLayoutManager);
                    pages = registeredUser.getPages();
                    addNewDataintoRecycler();

//                    mRecyclerViewChat.setLayoutManager(new LinearLayoutManager(getActivity()));
                    mRecyclerView.setAdapter(recyclerСlinicListAdapter);


                } else {
                    Toast.makeText(getActivity(), "Ошибочка", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseClinicList> call, Throwable t) {
                Toast.makeText(getActivity(), "An error occurred during networking", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void seeClinicListFilterMethod(String id, String speciality, String full_name) {
        App.getApi().seeclinicFiltered(id, full_name).enqueue(new Callback<ResponseClinicList>() {
            @Override
            public void onResponse(Call<ResponseClinicList> call, Response<ResponseClinicList> response) {

                int s = response.code();
                if (response.errorBody() != null) {
                    try {
                        Log.e("LOG", "Retrofit Response: " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                registeredUser = response.body();
                if (registeredUser.getDocuments() != null) {
                    CURRENT_PAGE_NUM = 1;
                    loading = false;
                    Toast.makeText(getActivity(), "SUCCESS", Toast.LENGTH_LONG).show();

                    GlobalVariables.documentClinicList = registeredUser.getDocuments();
                    recyclerСlinicListAdapter = new RecyclerСlinicListAdapter(GlobalVariables.documentClinicList, getActivity());

                    mRecyclerView.setLayoutManager(mLayoutManager);
                    pages = registeredUser.getPages();
                    addNewDataintoRecycler();

//                    mRecyclerViewChat.setLayoutManager(new LinearLayoutManager(getActivity()));
                    mRecyclerView.setAdapter(recyclerСlinicListAdapter);

                } else {
                    Toast.makeText(getActivity(), "Ошибочка", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseClinicList> call, Throwable t) {
                Toast.makeText(getActivity(), "An error occurred during networking", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loading = true;
                // Отменяем анимацию обновления
                mSwipeRefreshLayout.setRefreshing(false);
                seeClinicList();
                CURRENT_PAGE_NUM = 1;
            }
        }, 1000);
    }


}