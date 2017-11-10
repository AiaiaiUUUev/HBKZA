package com.example.d.healthbook.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.d.healthbook.Activities.DiseaseCategoryActivity;
import com.example.d.healthbook.Adapters.RecyclerAdapterDiseaseCategory;
import com.example.d.healthbook.Adapters.RecyclerAdapterDrugsCategory;
import com.example.d.healthbook.Appi.App;
import com.example.d.healthbook.Models.ResponseDiseaseCategory;
import com.example.d.healthbook.Models.ResponseDrugsCategory;
import com.example.d.healthbook.R;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by D on 19.06.2017.
 */

public class DiseaseCategoryFragment extends Fragment {
    private ResponseDiseaseCategory registeredUser;
    private RecyclerView mRecyclerView;
    private GridLayoutManager gridLayoutManager;
    private RecyclerAdapterDiseaseCategory mAdapter;
    private String id;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        if (getArguments() != null) {
            id = getArguments().getString("id");
        }
        seeDiseaseCategory(id);


    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //App.getInstance().setCurrentFragment(this);

        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_disease_category, container, false);


    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        App.getInstance().changeToolbarTitle((AppCompatActivity) getActivity(), "Список врачей");


        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewDiseaseCategory);


    }


    public void seeDiseaseCategory(String id) {
        App.getApi().seeDiseaseCategory(id).enqueue(new Callback<ResponseDiseaseCategory>() {
            @Override
            public void onResponse(Call<ResponseDiseaseCategory> call, Response<ResponseDiseaseCategory> response) {
                int s = response.code();
                if (response.errorBody() != null) {
                    try {
                        Log.e("LOG", "Retrofit Response: " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return;
                }
                registeredUser = response.body();


                Toast.makeText(getActivity(), "SUCCESS", Toast.LENGTH_LONG).show();
                gridLayoutManager = new GridLayoutManager(getActivity(), 2);
                mRecyclerView.setLayoutManager(gridLayoutManager);


                mAdapter = new RecyclerAdapterDiseaseCategory(registeredUser.getDocuments(), getActivity());

                mRecyclerView.setAdapter(mAdapter);


            }

            @Override
            public void onFailure(Call<ResponseDiseaseCategory> call, Throwable t) {
                Toast.makeText(getActivity(), "An error occurred during networking", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
