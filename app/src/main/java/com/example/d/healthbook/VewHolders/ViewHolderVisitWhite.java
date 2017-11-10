package com.example.d.healthbook.VewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.d.healthbook.R;

/**
 * Created by D on 07.07.2017.
 */

public class ViewHolderVisitWhite extends RecyclerView.ViewHolder {

    TextView timeVisitWhite;

    public TextView getTimeVisitWhite() {
        return timeVisitWhite;
    }

    public void setTimeVisitWhite(TextView timeVisitWhite) {
        this.timeVisitWhite = timeVisitWhite;
    }

    public ViewHolderVisitWhite(final View v) {
        super(v);
        timeVisitWhite = (TextView) v.findViewById(R.id.timeVisitWhite);
    }
}