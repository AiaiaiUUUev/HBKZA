package com.example.d.healthbook.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.d.healthbook.Activities.MainPageActivity;
import com.example.d.healthbook.Adapters.RecyclerAdapterVisit;
import com.example.d.healthbook.Appi.App;
import com.example.d.healthbook.GlobalVariables.GlobalVariables;
import com.example.d.healthbook.Models.ResponseVisit;
import com.example.d.healthbook.R;

import java.io.IOException;
import java.util.List;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by D on 20.06.2017.
 */

public class VisitFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private List<ResponseVisit> registeredUser;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private int CURRENT_PAGE_NUM=1;
    private int pages = 0;
    private int countPages = 1;
    private RecyclerAdapterVisit recyclerAdapterVisit;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        seeVisitUser();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //App.getInstance().setCurrentFragment(this);

        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.visit_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ((MainPageActivity)getActivity()).changeImageVisibility(0);
        //  Activity context = (Activity) Application.getInstance().getCurrentActivity();
        //  getActivity().getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((MainPageActivity) getActivity()).changeMenuItems(0);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_visit);
        mLayoutManager = new LinearLayoutManager(getActivity());
        App.getInstance().changeToolbarTitle((AppCompatActivity) getActivity(), "Визиты");
        App.getInstance().setCurrentFragment(this);





    }
    public void seeVisitUser() {
        App.getApi().seeVisit(GlobalVariables.user_auth_token).enqueue(new Callback<List<ResponseVisit>>() {
            @Override
            public void onResponse(Call<List<ResponseVisit>> call, Response<List<ResponseVisit>> response) {
                int s = response.code();
                if (response.errorBody() != null) {
                    try {
                        Log.e("LOG", "Retrofit Response: " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                registeredUser = response.body();
                if (registeredUser != null) {

                    Toast.makeText(getActivity(), "SUCCESS", Toast.LENGTH_LONG).show();

//                    GlobalVariables.documentList = registeredUser.getDocuments();

                    recyclerAdapterVisit = new RecyclerAdapterVisit(registeredUser, getActivity());

                    mRecyclerView.setLayoutManager(mLayoutManager);
//                    addNewDataintoRecycler();

//                    mRecyclerViewChat.setLayoutManager(new LinearLayoutManager(getActivity()));
                    mRecyclerView.setAdapter(recyclerAdapterVisit);


                } else {
                    Toast.makeText(getActivity(), "Ошибочка", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<ResponseVisit>> call, Throwable t) {
                Toast.makeText(getActivity(), "An error occurred during networking", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
