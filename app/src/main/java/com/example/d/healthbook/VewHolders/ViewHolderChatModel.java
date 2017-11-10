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

public class ViewHolderChatModel extends RecyclerView.ViewHolder {

    ImageView imageChatMan;
    TextView nameSurnameChatMan;
    TextView timeChat;
    TextView descriptionChat;
    Button quantityUnreadMessage;

    public ImageView getImageChatMan() {
        return imageChatMan;
    }

    public void setImageChatMan(ImageView imageChatMan) {
        this.imageChatMan = imageChatMan;
    }

    public TextView getNameSurnameChatMan() {
        return nameSurnameChatMan;
    }

    public void setNameSurnameChatMan(TextView nameSurnameChatMan) {
        this.nameSurnameChatMan = nameSurnameChatMan;
    }

    public TextView getTimeChat() {
        return timeChat;
    }

    public void setTimeChat(TextView timeChat) {
        this.timeChat = timeChat;
    }

    public TextView getDescriptionChat() {
        return descriptionChat;
    }

    public void setDescriptionChat(TextView descriptionChat) {
        this.descriptionChat = descriptionChat;
    }

    public Button getQuantityUnreadMessage() {
        return quantityUnreadMessage;
    }

    public void setQuantityUnreadMessage(Button quantityUnreadMessage) {
        this.quantityUnreadMessage = quantityUnreadMessage;
    }


    public ViewHolderChatModel(View v) {
        super(v);
        imageChatMan = (ImageView) v.findViewById(R.id.imageChatMan);
        nameSurnameChatMan = (TextView) v.findViewById(R.id.nameSurnameChatMan);
        timeChat = (TextView) v.findViewById(R.id.timeChat);
        descriptionChat = (TextView) v.findViewById(R.id.descriptionChat);
        quantityUnreadMessage = (Button) v.findViewById(R.id.quantityUnreadMessage);

    }


}