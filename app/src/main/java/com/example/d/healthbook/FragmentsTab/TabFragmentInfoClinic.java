package com.example.d.healthbook.FragmentsTab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.d.healthbook.Models.ResponseClinicInfo;
import com.example.d.healthbook.Models.ResponseClinicInfo2;
import com.example.d.healthbook.Models.ResponseDoctorInfo;
import com.example.d.healthbook.R;

/**
 * Created by D on 09.06.2017.
 */

public class TabFragmentInfoClinic extends Fragment {
    private TextView textView;
    private boolean isViewCreated = false;
    ResponseClinicInfo2 mainData;

    public void upDateData(ResponseClinicInfo2 data) {
        if (data != null) {
            mainData = data;
            setDataToViews();
        }
    }


    public void setDataToViews() {
        if (mainData != null && isViewCreated)
            textView.setText(mainData.getInfo());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_fragment_clinic_info, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        textView = (TextView) view.findViewById(R.id.pagerTVClinicInfo);
        isViewCreated = true;
        setDataToViews();

    }


}