package com.example.d.healthbook.FragmentsTab;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.d.healthbook.Models.ResponseGetUserData;
import com.example.d.healthbook.R;

/**
 * Created by D on 01.07.2017.
 */

public class TabUserInfoMydata extends Fragment {


    private TextView userGender;
    private TextView birthdateUser;
    private boolean isViewCreated = false;
    private ResponseGetUserData mainData;

    public void upDateData(ResponseGetUserData data) {
        if (data != null) {
            mainData = data;
            setDataToViews();
        }
    }

    public void setDataToViews() {
        if (mainData != null && isViewCreated) {
            userGender.setText(mainData.getGender());
            birthdateUser.setText(mainData.getBirthday());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_fragment_user_info_my_date, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        userGender = (TextView) view.findViewById(R.id.userGender);
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Myriad Pro Bold.ttf");
        userGender.setTypeface(font);
        birthdateUser = (TextView) view.findViewById(R.id.birthdateUser);
        birthdateUser.setTypeface(font);
        isViewCreated = true;
        setDataToViews();

    }
}
