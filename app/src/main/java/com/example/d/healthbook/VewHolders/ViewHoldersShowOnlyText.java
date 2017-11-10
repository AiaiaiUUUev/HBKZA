package com.example.d.healthbook.VewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.d.healthbook.R;

/**
 * Created by D on 03.07.2017.
 */

public class ViewHoldersShowOnlyText extends RecyclerView.ViewHolder {


    TextView userProgressTVONE;

    public TextView getUserProgressTVONE() {
        return userProgressTVONE;
    }

    public void setUserProgressTVONE(TextView userProgressTVONE) {
        this.userProgressTVONE = userProgressTVONE;
    }

    public ViewHoldersShowOnlyText(final View v) {
        super(v);
        userProgressTVONE = (TextView) v.findViewById(R.id.userProgressTVONE);

    }
}