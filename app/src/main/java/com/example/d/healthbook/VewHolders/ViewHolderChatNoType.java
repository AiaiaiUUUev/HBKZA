package com.example.d.healthbook.VewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.d.healthbook.R;

/**
 * Created by D on 28.06.2017.
 */

public class ViewHolderChatNoType extends RecyclerView.ViewHolder {


    public TextView getTexMessage() {
        return texMessage;
    }

    public void setTexMessage(TextView texMessage) {
        this.texMessage = texMessage;
    }

    private TextView texMessage;

    public TextView getTimeCome() {
        return timeCome;
    }

    public void setTimeCome(TextView timeCome) {
        this.timeCome = timeCome;
    }

    private TextView timeCome;

    public ViewHolderChatNoType(View v) {
        super(v);
        texMessage = (TextView) v.findViewById(R.id.txtMessageCome);
        timeCome = (TextView) v.findViewById(R.id.createdTimeMessageCome);

    }


}