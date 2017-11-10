package com.example.d.healthbook.Fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.d.healthbook.Activities.MainPageActivity;
import com.example.d.healthbook.Adapters.RecyclerAdapterChannel;
import com.example.d.healthbook.Adapters.RecyclerDoctorListAdapter;
import com.example.d.healthbook.Appi.App;
import com.example.d.healthbook.GlobalVariables.GlobalVariables;
import com.example.d.healthbook.Models.ResponseChannel;
import com.example.d.healthbook.Models.ResponseDoctorList;
import com.example.d.healthbook.R;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by D on 19.06.2017.
 */

public class ChannelFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private ResponseChannel registeredUser;
    private RecyclerView mRecyclerView;
    private RecyclerAdapterChannel recyclerDoctorListAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    private LinearLayoutManager mLayoutManager;
    private int pages = 0;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

            setHasOptionsMenu(true);
            seeChannel();


    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //App.getInstance().setCurrentFragment(this);
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.search_doctors_fragment, container, false);

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        App.getInstance().changeToolbarTitle((AppCompatActivity) getActivity(), "Список врачей");
//        ((MainPageActivity) getActivity()).changeMenuItems(0);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.doctorlist_recycler);
        mLayoutManager = new LinearLayoutManager(getActivity());


        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
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
                                Toast.makeText(getActivity(), "Загрузка списка докторов", Toast.LENGTH_SHORT).show();
                                addChannel(CURRENT_PAGE_NUM + 1);
                            }
                        }
                    }
                }
            }
        });
    }

    public void addChannel(Integer page_num) {
        App.getApi().addChannel(page_num).enqueue(new Callback<ResponseChannel>() {
            @Override
            public void onResponse(Call<ResponseChannel> call, Response<ResponseChannel> response) {
                int s = response.code();
                if (response.errorBody() != null) {
                    try {
                        Log.e("LOG", "Retrofit Response: " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                registeredUser = response.body();
                if (registeredUser.getList() != null) {
                    loading = false;
                    CURRENT_PAGE_NUM++;
                    Log.e("CURRENT_PAGE_NUM", "" + CURRENT_PAGE_NUM);
                    Toast.makeText(getActivity(), "Загрузка прошла успешно ", Toast.LENGTH_SHORT).show();
                    Log.e("Data size ", "" + registeredUser.getList().size());
                    for (int i = 0; i < registeredUser.getList().size(); i++) {
                        Log.e("Iteration", "" + i);
                        GlobalVariables.listChannels.add(registeredUser.getList().get(i));
                    }
                    recyclerDoctorListAdapter.notifyDataSetChanged();
                    if (recyclerDoctorListAdapter == null)
                        return;
                    Log.e("GlobalVariables SIZE ", "" + GlobalVariables.listChannels.size());

                } else {
                    Toast.makeText(getActivity(), "Ошибочка", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseChannel> call, Throwable t) {
                Toast.makeText(getActivity(), "An error occurred during networking", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void seeChannel() {
        App.getApi().seeChannel().enqueue(new Callback<ResponseChannel>() {
            @Override
            public void onResponse(Call<ResponseChannel> call, Response<ResponseChannel> response) {
                int s = response.code();
                if (response.errorBody() != null) {
                    try {
                        Log.e("LOG", "Retrofit Response: " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                registeredUser = response.body();
                if (registeredUser.getList() != null) {
                    CURRENT_PAGE_NUM = 1;
                    loading = false;
                    Toast.makeText(getActivity(), "SUCCESS", Toast.LENGTH_LONG).show();

                    GlobalVariables.listChannels = registeredUser.getList();

                    recyclerDoctorListAdapter = new RecyclerAdapterChannel(GlobalVariables.listChannels, getActivity());

                    mRecyclerView.setLayoutManager(mLayoutManager);
                    pages = registeredUser.getPages();
                    addNewDataintoRecycler();

//                    mRecyclerViewChat.setLayoutManager(new LinearLayoutManager(getActivity()));
                    mRecyclerView.setAdapter(recyclerDoctorListAdapter);


                } else {
                    Toast.makeText(getActivity(), "Ошибочка", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseChannel> call, Throwable t) {
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
                seeChannel();
                CURRENT_PAGE_NUM = 1;
            }
        }, 1000);
    }

}
