package com.example.d.healthbook.VewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.d.healthbook.R;

/**
 * Created by D on 20.07.2017.
 */

public class ViewHolderChatGroupModels extends RecyclerView.ViewHolder {

    ImageView imageGroup;
    TextView titleGroup;
    TextView listPeopleGroup;

    public ImageView getImageGroup() {
        return imageGroup;
    }

    public void setImageGroup(ImageView imageGroup) {
        this.imageGroup = imageGroup;
    }

    public TextView getTitleGroup() {
        return titleGroup;
    }

    public void setTitleGroup(TextView titleGroup) {
        this.titleGroup = titleGroup;
    }

    public TextView getListPeopleGroup() {
        return listPeopleGroup;
    }

    public void setListPeopleGroup(TextView listPeopleGroup) {
        this.listPeopleGroup = listPeopleGroup;
    }

    public ViewHolderChatGroupModels(View v) {
        super(v);
        imageGroup = (ImageView) v.findViewById(R.id.imageGroup);
        titleGroup = (TextView) v.findViewById(R.id.titleGroup);
        listPeopleGroup = (TextView) v.findViewById(R.id.listPeopleGroup);

    }


}
