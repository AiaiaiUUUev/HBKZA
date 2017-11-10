package com.example.d.healthbook.Fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.d.healthbook.Activities.MainPageActivity;
import com.example.d.healthbook.Adapters.RecyclerFeedChangeableRowAdapter;
import com.example.d.healthbook.Adapters.RecyclerFeedListAdapter;
import com.example.d.healthbook.Adapters.RecyclerNewsListAdapter;
import com.example.d.healthbook.Appi.API_Controller;
import com.example.d.healthbook.Appi.App;
import com.example.d.healthbook.GlobalVariables.GlobalVariables;
import com.example.d.healthbook.Models.ResponseDoctorList;
import com.example.d.healthbook.Models.ResponseFeedList;
import com.example.d.healthbook.R;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Query;

/**
 * Created by D on 15.06.2017.
 */

public class FeedListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private ResponseFeedList registeredUser;
    private RecyclerView mRecyclerView;
    private RecyclerFeedChangeableRowAdapter recycler_news_list;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    private LinearLayoutManager mLayoutManager;
    private int pages = 0;
    private int countPages = 1;
    private String newsFeedSt = "";
    private String status = "";
    private String allSt = "";
    private ProgressDialog progressDialog;
    private ProgressBar progressBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        if (getArguments() != null) {
            newsFeedSt = getArguments().getString("newsFeedSt");
            status = getArguments().getString("status");
            allSt = getArguments().getString("allSt");
            if (allSt == null) {
                allSt = "";
            }
            if (allSt.equals("news,status")) {
                seeFeedMethod(1, "ru", "news", "news,statuses");
            } else if (newsFeedSt.equals("news")) {
                seeFeedMethod(1, "ru", "news", "news");
            } else if (status.equals("status")) {
                seeFeedMethod(1, "ru", "news", "statuses");
            } else {
                seeFeedMethod(1, "ru", "news", "news,statuses");
            }

        } else {
            seeFeedMethod(1, "ru", "news", "news,statuses");
        }


    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //App.getInstance().setCurrentFragment(this);

        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.feed_list_fragment, container, false);

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        App.getInstance().changeToolbarTitle((AppCompatActivity) getActivity(), "Лента");

        ((MainPageActivity) getActivity()).changeMenuItems(1);

        progressBar = (ProgressBar) view.findViewById(R.id.progressBarFeed);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.feed_recycler);
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
                                Toast.makeText(getActivity(), "Загрузка списка новостей", Toast.LENGTH_SHORT).show();
//                                addNewsList(CURRENT_PAGE_NUM + 1);
                            }
                        }
                    }
                }
            }
        });
    }

//    public void addNewsList(Integer page_num) {
//        App.getApi().addNewsList(page_num, 1).enqueue(new Callback<ResponseDoctorList>() {
//            @Override
//            public void onResponse(Call<ResponseDoctorList> call, Response<ResponseDoctorList> response) {
//                int s = response.code();
//                if (response.errorBody() != null) {
//                    try {
//                        Log.e("LOG", "Retrofit Response: " + response.errorBody().string());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//                registeredUser = response.body();
//                if (registeredUser.getDocuments() != null) {
//                    loading = false;
//                    CURRENT_PAGE_NUM++;
//                    Log.e("CURRENT_PAGE_NUM", "" + CURRENT_PAGE_NUM);
//                    Toast.makeText(getActivity(), "Загрузка прошла успешно ", Toast.LENGTH_SHORT).show();
//                    Log.e("Data size ", "" + registeredUser.getDocuments().size());
//                    for (int i = 0; i < registeredUser.getDocuments().size(); i++) {
//                        Log.e("Iteration", "" + i);
//                        GlobalVariables.documentList.add(registeredUser.getDocuments().get(i));
//                    }
//                    recycler_news_list.notifyDataSetChanged();
//                    if (recycler_news_list == null)
//                        return;
//                    Log.e("GlobalVariables SIZE ", "" + GlobalVariables.documentList.size());
//
//                } else {
//                    Toast.makeText(getActivity(), "Ошибочка", Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseDoctorList> call, Throwable t) {
//                Toast.makeText(getActivity(), "An error occurred during networking", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

    public void seeFeedMethod(Integer page, String lang, String type, String scope) {
//        App.getApi().seeFeedList(GlobalVariables.user_auth_token, page, lang, type, scope)
        App.getApi().seeFeedList2(GlobalVariables.user_auth_token).enqueue(new Callback<ResponseFeedList>() {
            @Override
            public void onResponse(Call<ResponseFeedList> call, Response<ResponseFeedList> response) {
                int s = response.code();
                if (response.errorBody() != null) {
                    try {
                        Log.e("LOG", "Retrofit Response: " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
//                progressDialog.dismiss();
                progressBar.setVisibility(View.GONE);
                registeredUser = response.body();
                if (registeredUser.getDocuments() != null) {
                    CURRENT_PAGE_NUM = 1;
                    loading = false;
//                    Toast.makeText(getActivity(), "SUCCESS", Toast.LENGTH_LONG).show();

//                    GlobalVariables.documentList = registeredUser.getDocuments();

                    if (registeredUser.getDocuments().size() == 0) {
                        Toast.makeText(getActivity(), "Ничего не найдено", Toast.LENGTH_SHORT).show();
                    }

                    recycler_news_list = new RecyclerFeedChangeableRowAdapter(registeredUser.getDocuments(), getActivity());

                    mRecyclerView.setLayoutManager(mLayoutManager);
                    pages = registeredUser.getPages();
                    addNewDataintoRecycler();

//                    mRecyclerViewChat.setLayoutManager(new LinearLayoutManager(getActivity()));
                    mRecyclerView.setAdapter(recycler_news_list);


                } else {
                    Toast.makeText(getActivity(), "Ошибочка", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseFeedList> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
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
                seeFeedMethod(1, "ru", "news", "news,statuses");
                CURRENT_PAGE_NUM = 1;
            }
        }, 1000);
    }

}
