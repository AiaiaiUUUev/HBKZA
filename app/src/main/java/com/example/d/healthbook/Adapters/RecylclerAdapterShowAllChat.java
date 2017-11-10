package com.example.d.healthbook.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.d.healthbook.Activities.ChatActivityShowMessage;
import com.example.d.healthbook.Models.ChatModel;
import com.example.d.healthbook.Models.ChatRealmListModel;
import com.example.d.healthbook.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.realm.RealmResults;

/**
 * Created by D on 19.07.2017.
 */

public class RecylclerAdapterShowAllChat extends RecyclerView.Adapter<RecylclerAdapterShowAllChat.ViewHolder> {

    private Context context;
    private RealmResults<ChatRealmListModel> chatModels;


    public RecylclerAdapterShowAllChat(RealmResults<ChatRealmListModel> chatRealmListModels, Context context) {
        chatModels = chatRealmListModels;
        this.context = context;

    }


    @Override
    public RecylclerAdapterShowAllChat.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_one_man_chat, parent, false);

        return new RecylclerAdapterShowAllChat.ViewHolder((View) view);
    }

    @Override
    public void onBindViewHolder(RecylclerAdapterShowAllChat.ViewHolder holder, final int position) {
//        MainController.setImageToViewById(context, chatModels.get(position).getPhoto(), holder.imageDocSubscription);
//        Glide.with(context).load("ffff").centerCrop().into(holder.imageChatMan);
        int size = chatModels.get(position).getChatModels().size();
        holder.descriptionChat.setText(chatModels.get(position).getChatModels().get(size - 1).getTextMessage());


        int quantityUnreadMessage = 0;

        for (int i = 0; i < chatModels.get(position).getChatModels().size(); i++) {
            if (!chatModels.get(position).getChatModels().get(i).isSeeOrNotMessage()) {
                quantityUnreadMessage++;
            }

        }
        holder.quantityUnreadMessage.setText(quantityUnreadMessage + "");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ChatActivityShowMessage.class);
                intent.putExtra("fromRecycler",true);
                intent.putExtra("position",position);
                context.startActivity(intent);
            }
        });


    }


    @Override
    public int getItemCount() {
        return chatModels.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageChatMan;
        TextView nameSurnameChatMan;
        TextView timeChat;
        TextView descriptionChat;
        Button quantityUnreadMessage;


        public ViewHolder(final View v) {
            super(v);
            imageChatMan = (ImageView) v.findViewById(R.id.imageChatMan);
            nameSurnameChatMan = (TextView) v.findViewById(R.id.nameSurnameChatMan);
            timeChat = (TextView) v.findViewById(R.id.timeChat);
            descriptionChat = (TextView) v.findViewById(R.id.descriptionChat);
            quantityUnreadMessage = (Button) v.findViewById(R.id.quantityUnreadMessage);


        }
    }

}
