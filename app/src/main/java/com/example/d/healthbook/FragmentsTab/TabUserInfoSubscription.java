package com.example.d.healthbook.FragmentsTab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.d.healthbook.Adapters.RecyclerAdapterAllSubscriptionToDoc;
import com.example.d.healthbook.GlobalVariables.GlobalVariables;
import com.example.d.healthbook.Models.ResponseAllSubscriptionsToDoctor;
import com.example.d.healthbook.R;

import java.util.List;

/**
 * Created by D on 01.07.2017.
 */

public class TabUserInfoSubscription extends Fragment {


    private RecyclerView mRecycler;

    private boolean isViewCreated = false;
    private List<ResponseAllSubscriptionsToDoctor> mainData;
    private ResponseAllSubscriptionsToDoctor registeredUser;
    private RecyclerAdapterAllSubscriptionToDoc adapter;
    private TextView phoneNumber;

    public void upDateData(List<ResponseAllSubscriptionsToDoctor> data) {
        if (data != null) {
            mainData = data;
            setDataToViews();
        }
    }

    public void setDataToViews() {
        if (mainData != null && isViewCreated) {
            phoneNumber.setText(GlobalVariables.responseGetUserData.getPhone());
            adapter = new RecyclerAdapterAllSubscriptionToDoc(mainData, getActivity());
            mRecycler.setAdapter(adapter);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_fragment_user_info_subscription, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mRecycler = (RecyclerView) view.findViewById(R.id.recycler_subscription_user_info);
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        phoneNumber = (TextView) view.findViewById(R.id.infoSubsctiptionNumberPhone);
        isViewCreated = true;
        setDataToViews();

    }


}
