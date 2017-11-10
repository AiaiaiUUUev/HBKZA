package com.example.d.healthbook.FragmentsTab;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.d.healthbook.Activities.DoctorActivityInfo;
import com.example.d.healthbook.Appi.App;
import com.example.d.healthbook.Models.ResponseDoctorInfo;
import com.example.d.healthbook.R;
import com.example.d.healthbook.View.DoctorInterface;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by D on 02.06.2017.
 */


public class TabFragmentInfoDoc extends Fragment {
    private TextView textView;
    private boolean isViewCreated = false;
    ResponseDoctorInfo mainData;


    public void upDateData(ResponseDoctorInfo data) {
        if (data != null) {
            mainData = data;
            setDataToViews();
        }
    }

    public void setDataToViews() {
        if (mainData != null && isViewCreated)
            textView.setText(mainData.getInfoDs());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_fragment_doc_info, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        textView = (TextView) view.findViewById(R.id.pagerTVdocInfo);
        isViewCreated = true;
        setDataToViews();

    }
}