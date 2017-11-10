package com.example.d.healthbook.Fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.d.healthbook.Activities.MainPageActivity;
import com.example.d.healthbook.Appi.App;
import com.example.d.healthbook.GlobalVariables.GlobalVariables;
import com.example.d.healthbook.R;

/**
 * Created by D on 15.06.2017.
 */

public class DialogFragmentFeedFilter extends DialogFragment implements View.OnClickListener {

    final String LOG_TAG = "myLogs";
    private String newsFeedSt = "";
    private String statusSt = "";
    private CheckBox news_chBox_Feed;
    private CheckBox status_chBox_Feed;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().setTitle("Title!");
        View v = inflater.inflate(R.layout.dialog_fragment_filter_feed, null);

        v.findViewById(R.id.allCompilationBtnDialog).setOnClickListener(this);
        v.findViewById(R.id.clickBtnFilterFeed).setOnClickListener(this);

        news_chBox_Feed = (CheckBox) v.findViewById(R.id.news_chBox_Feed);
        status_chBox_Feed = (CheckBox) v.findViewById(R.id.status_chBox_Feed);


        news_chBox_Feed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    newsFeedSt = "news";
                else {
                    newsFeedSt = "";
                }
            }
        });

        status_chBox_Feed.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    statusSt = "status";
                else {
                    statusSt = "";
                }
            }
        });

        return v;

    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.allCompilationBtnDialog: {
                news_chBox_Feed.setChecked(true);
                status_chBox_Feed.setChecked(true);

                break;
            }
            case R.id.clickBtnFilterFeed: {

                Bundle bundle = new Bundle();
                if (newsFeedSt.equals("news") && statusSt.equals("status")){
                    bundle.putString("allSt", "news,status");
                }else{
                    bundle.putString("newsFeedSt", newsFeedSt);
                    bundle.putString("status", statusSt);
                }
//                bundle.putString("newsFeedSt", newsFeedSt);
//                bundle.putString("status", statusSt);
//                bundle.putString("all", "news,status");

                App.getInstance().setFragmentBottom((MainPageActivity) getActivity(), FeedListFragment.class.getName(),
                        android.R.anim.fade_in, android.R.anim.fade_out, false, bundle);
                dismiss();
                break;
            }

        }


    }

    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        Log.d(LOG_TAG, "Dialog 1: onDismiss");
    }

    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        Log.d(LOG_TAG, "Dialog 1: onCancel");
    }
}
