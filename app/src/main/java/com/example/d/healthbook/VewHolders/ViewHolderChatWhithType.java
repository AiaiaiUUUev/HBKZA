package com.example.d.healthbook.VewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.d.healthbook.R;

/**
 * Created by D on 28.06.2017.
 */

public class ViewHolderChatWhithType  extends RecyclerView.ViewHolder {


    public TextView getTexMessage() {
        return texMessage;
    }

    public void setTexMessage(TextView texMessage) {
        this.texMessage = texMessage;
    }

    private TextView texMessage;
    private TextView timeCome;

    public ViewHolderChatWhithType(View v) {
        super(v);
        texMessage = (TextView) v.findViewById(R.id.txtMessageSent);
        timeCome = (TextView) v.findViewById(R.id.createdTimeMessageSent);

    }


}