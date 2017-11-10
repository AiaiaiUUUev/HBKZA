package com.example.d.healthbook.FragmentsTab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.d.healthbook.Models.ResponseDoctorInfo;
import com.example.d.healthbook.R;

/**
 * Created by D on 02.06.2017.
 */

public class TabFragmentSchedule extends Fragment {


    ResponseDoctorInfo mainData;
    private TextView textView;
    private boolean isViewCreated = false;

    public void upDateData(ResponseDoctorInfo data) {
        if (data != null) {
            mainData = data;
            setDataToViews();
        }
    }

    public void setDataToViews() {
        if (mainData != null && isViewCreated)
            if (mainData.getSchedule().size() != 0) {
                textView.setText(mainData.getSchedule().get(0).getDay() + ":" + mainData.getSchedule().get(0).getStartTime() + "/"
                        + mainData.getSchedule().get(0).getFinishTime());
            }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_fragment_shedule, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        textView = (TextView) view.findViewById(R.id.pagetSheduledocTV);
        isViewCreated = true;
        setDataToViews();

    }
}
