package com.example.d.healthbook.VewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.d.healthbook.R;

/**
 * Created by D on 03.07.2017.
 */

public class ViewHolderShowTasks extends RecyclerView.ViewHolder {

    public TextView getInfoProgressTV() {
        return infoProgressTV;
    }

    public void setInfoProgressTV(TextView infoProgressTV) {
        this.infoProgressTV = infoProgressTV;
    }

    public CheckBox getProgressChBx() {
        return progressChBx;
    }

    public void setProgressChBx(CheckBox progressChBx) {
        this.progressChBx = progressChBx;
    }

    TextView infoProgressTV;
    CheckBox progressChBx;
    ImageView checkboxIMG;


    public ViewHolderShowTasks(final View v) {
        super(v);
        infoProgressTV = (TextView) v.findViewById(R.id.infoProgressTV);
        progressChBx = (CheckBox) v.findViewById(R.id.progressChBx);
        checkboxIMG= (ImageView) v.findViewById(R.id.checkboxIMG);


    }
}