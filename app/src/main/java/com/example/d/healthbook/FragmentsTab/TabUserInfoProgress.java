package com.example.d.healthbook.FragmentsTab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.d.healthbook.Adapters.RecyclerAdapterProgressUser;
import com.example.d.healthbook.Adapters.RecyclerAdapterProgressUserExpandable;
import com.example.d.healthbook.Models.ModelWhithTasks;
import com.example.d.healthbook.Models.ResponseAllSubscriptionsToDoctor;
import com.example.d.healthbook.Models.ResponseProgressUser;
import com.example.d.healthbook.Models.Task;
import com.example.d.healthbook.R;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by D on 01.07.2017.
 */

public class TabUserInfoProgress extends Fragment {

    private List<Task> tasks = new ArrayList<>();
    private List<Object> tasksObjects = new ArrayList<>();
    private RecyclerView mRecycler;
    private CircularProgressBar circularProgressBar;
    private ProgressBar line_progressBar;
    private TextView procentTV;

    private boolean isViewCreated = false;
    private ResponseProgressUser mainData;
    private ResponseAllSubscriptionsToDoctor registeredUser;
//    private RecyclerAdapterProgressUser customPagerAdapter;
    private RecyclerAdapterProgressUserExpandable adapter;
    private List<ModelWhithTasks> modelWhithTaskses;

    public void upDateData(ResponseProgressUser data) {
        if (data != null) {
            mainData = data;
            setDataToViews();
        }
    }

    public void setDataToViews() {
        if (mainData != null && isViewCreated) {
            int progressGet = (int) Math.round(mainData.getProgress());
            int animationDuration = 2500;
//            circularProgressBar.setProgressWithAnimation(progressGet, animationDuration);
            line_progressBar.setProgress(progressGet);

            procentTV.setText(progressGet + " %");

            modelWhithTaskses = new ArrayList<>();
            for (int i = 0; i < mainData.getMissions().size(); i++) {
                tasksObjects.add(mainData.getMissions().get(i).getMissionName());
                String name=mainData.getMissions().get(i).getMissionName();
                List<Task>tasks=new ArrayList<>();

                for (int j = 0; j < mainData.getMissions().get(i).getTasks().size(); j++) {
//                    tasks.add(mainData.getMissions().get(i).getTasks().get(j));
                    tasksObjects.add(mainData.getMissions().get(i).getTasks().get(j));
                    tasks.add(mainData.getMissions().get(i).getTasks().get(j));



                }
                ModelWhithTasks modelWhithTasks = new ModelWhithTasks(tasks,name);
                modelWhithTaskses.add(modelWhithTasks);

            }
//            customPagerAdapter = new RecyclerAdapterProgressUser(tasksObjects, getActivity());
            adapter= new RecyclerAdapterProgressUserExpandable(getActivity(),modelWhithTaskses);

            mRecycler.setAdapter(adapter);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_fragment_user_info_progress, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mRecycler = (RecyclerView) view.findViewById(R.id.recycler_progress_user_info);
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        circularProgressBar = (CircularProgressBar) view.findViewById(R.id.circularProgressbar);
        line_progressBar= (ProgressBar) view.findViewById(R.id.line_progressBar);
        procentTV = (TextView) view.findViewById(R.id.procentTV);


        isViewCreated = true;
        setDataToViews();

    }


}
