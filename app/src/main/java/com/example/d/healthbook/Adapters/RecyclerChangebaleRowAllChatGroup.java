package com.example.d.healthbook.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.d.healthbook.Activities.ChatActivityShowMessage;
import com.example.d.healthbook.Models.ChatRealmListModel;
import com.example.d.healthbook.R;
import com.example.d.healthbook.VewHolders.ViewHolderChatGroupModels;
import com.example.d.healthbook.VewHolders.ViewHolderChatModel;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by D on 20.07.2017.
 */

public class RecyclerChangebaleRowAllChatGroup extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG1 = "MY LIST ADAPTER";
    private Context context;
    private RealmResults<ChatRealmListModel> documents;
    private final int GROUP_MODEL = 0, CHAT_MODEL = 1;
    private Realm mRealm;


    public RecyclerChangebaleRowAllChatGroup(RealmResults<ChatRealmListModel> documentsUrl, Context context) {
        documents = documentsUrl;
        this.context = context;
        mRealm = Realm.getInstance(context);


    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case CHAT_MODEL:
                View v1 = inflater.inflate(R.layout.row_item_one_man_chat, parent, false);
                viewHolder = new ViewHolderChatModel(v1);
                break;
            case GROUP_MODEL:
                View v2 = inflater.inflate(R.layout.row_item_group_already_added, parent, false);
                viewHolder = new ViewHolderChatGroupModels(v2);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        switch (holder.getItemViewType()) {
            case CHAT_MODEL:
                ViewHolderChatModel vh1 = (ViewHolderChatModel) holder;
                configureViewHolder1(vh1, position);
                break;
            case GROUP_MODEL:
                ViewHolderChatGroupModels vh2 = (ViewHolderChatGroupModels) holder;
                configureViewHolder2(vh2, position);
                break;

        }


    }

    private void configureViewHolder2(ViewHolderChatGroupModels vh2, int position) {
        int size = documents.get(position).getGroupModel().size();
        vh2.getTitleGroup().setText(documents.get(position).getGroupModel().get(size - 1).getTitleGroup());
        vh2.getListPeopleGroup().setText(documents.get(position).getGroupModel().get(size - 1).getAllGroupPeople());


        vh2.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void configureViewHolder1(ViewHolderChatModel vh1, final int position) {

        int size = documents.get(position).getChatModels().size();

        int quantityUnreadMessage = 0;

        vh1.getNameSurnameChatMan().setText(documents.get(position).getNameSurname());
//        vh1.getImageChatMan().setImageBitmap(MainController.decodeSampledBitmapFromResource(context.getResources(),
//                documents.get(position).getImageMan(), 100, 100));

        vh1.getImageChatMan().setImageBitmap(
                getBitmapFromResources(context.getResources(), documents.get(position).getImageMan()));


        for (int i = 0; i < documents.get(position).getChatModels().size(); i++) {
            if (!documents.get(position).getChatModels().get(i).isSeeOrNotMessage()) {
                quantityUnreadMessage++;
            }

        }

        if (size != 0) {
            vh1.getDescriptionChat().setText(documents.get(position).getChatModels().get(size - 1).getTextMessage());
        }

        if (quantityUnreadMessage == 0) {
            vh1.getQuantityUnreadMessage().setVisibility(View.GONE);
        } else {
            vh1.getQuantityUnreadMessage().setVisibility(View.VISIBLE);
            vh1.getQuantityUnreadMessage().setText(quantityUnreadMessage + "");
            vh1.getDescriptionChat().setText(documents.get(position).getChatModels().get(size - 1).getTextMessage());


        }


        vh1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChatActivityShowMessage.class);
                intent.putExtra("fromRecycler", true);
                intent.putExtra("name", documents.get(position).getNameSurname());
                intent.putExtra("position", position);
                intent.putExtra("img", documents.get(position).getImageMan());
                context.startActivity(intent);

            }
        });
    }


    public static Bitmap getBitmapFromResources(Resources resources, int resImage) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inDither = false;
        options.inSampleSize = 1;
        options.inScaled = false;
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;

        return BitmapFactory.decodeResource(resources, resImage, options);
    }

    @Override
    public int getItemViewType(int position) {
        int type = documents.get(position).getType();

        if (type == 1) {
            return CHAT_MODEL;
        } else if (type == 2) {
            return GROUP_MODEL;
        }
        return -1;
    }

    @Override
    public int getItemCount() {
        return documents.size();
    }


}