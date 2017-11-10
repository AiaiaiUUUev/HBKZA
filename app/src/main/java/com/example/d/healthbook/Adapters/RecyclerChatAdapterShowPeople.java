package com.example.d.healthbook.Adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.d.healthbook.Activities.ChatActivityChooseManForGroup;
import com.example.d.healthbook.Controller.MainController;
import com.example.d.healthbook.Models.ChatPeopleAddGroup;
import com.example.d.healthbook.R;

import java.util.List;

import static com.example.d.healthbook.Activities.ChatActivityChooseManForGroup.positionItem;

/**
 * Created by D on 19.07.2017.
 */

public class RecyclerChatAdapterShowPeople extends RecyclerView.Adapter<RecyclerChatAdapterShowPeople.ViewHolder> {

    private Context context;
    private List<ChatPeopleAddGroup> chatModels;
    private boolean addOne;


    public RecyclerChatAdapterShowPeople(List<ChatPeopleAddGroup> listModels, Context context, boolean addOne) {
        chatModels = listModels;
        this.context = context;
        this.addOne = addOne;

    }


    @Override
    public RecyclerChatAdapterShowPeople.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_show_people_chat_add_group, parent, false);

        return new RecyclerChatAdapterShowPeople.ViewHolder((View) view);
    }

    @Override
    public void onBindViewHolder(final RecyclerChatAdapterShowPeople.ViewHolder holder, final int position) {
//        MainController.setImageToViewById(context, chatModels.get(position).getPhoto(), holder.imageDocSubscription);
//        Glide.with(context).load("ffff").centerCrop().into(holder.imageChatMan);
//        Bitmap icon = BitmapFactory.decodeResource(context.getResources(), chatModels.get(position).getImage());
//        holder.imagePeopleChat.setImageBitmap(icon);

        holder.nameSurnameChatPeople.setText(chatModels.get(position).getName());

//        holder.imagePeopleChat.setImageBitmap(
//                MainController.decodeSampledBitmapFromResource(context.getResources(), chatModels.get(position).getImage(), 100, 100));

        holder.imagePeopleChat.setImageBitmap(
                getBitmapFromResources(context.getResources(), chatModels.get(position).getImage()));

        if (!addOne) {
            checkSelectedItems(holder.getAdapterPosition(), holder);
        } else {
            checkSelectedItemsAddOne(holder.getAdapterPosition(), holder);
        }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectItem(holder, position, addOne);


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

    private void selectItem(ViewHolder holder, int position, boolean addOne) {
        holder.not_selected.setVisibility(View.GONE);
        holder.selected_whith_gray.setVisibility(View.GONE);
        holder.selected_green.setVisibility(View.GONE);


        if (chatModels.get(holder.getAdapterPosition()).isSelected()) {
            holder.not_selected.setVisibility(View.VISIBLE);
            chatModels.get(holder.getAdapterPosition()).setSelected(false);
        } else {
            holder.selected_whith_gray.setVisibility(View.VISIBLE);
            chatModels.get(holder.getAdapterPosition()).setNowSelected(true);
            chatModels.get(holder.getAdapterPosition()).setSelected(true);
        }


        if (positionItem == -1) {
            positionItem = position;
        }
        if (positionItem != position) {
            if (!addOne) {
                chatModels.get(positionItem).setNowSelected(false);
                notifyItemChanged(positionItem);
                positionItem = position;
            } else {
                notifyItemChanged(positionItem);
                chatModels.get(positionItem).setNowSelected(false);
                chatModels.get(positionItem).setSelected(false);
                positionItem = position;
            }


        }

        if (!addOne)
            ((ChatActivityChooseManForGroup) context).checkIfAlreadyAdd(chatModels, position);
        else
            ((ChatActivityChooseManForGroup) context).checkIfAlreadyAddOneMan(chatModels, position);

    }


    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return chatModels.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imagePeopleChat;
        TextView nameSurnameChatPeople;
        ImageView not_selected;
        ImageView selected_whith_gray;
        ImageView selected_green;


        public ViewHolder(final View v) {
            super(v);
            imagePeopleChat = (ImageView) v.findViewById(R.id.imagePeopleChat);
            nameSurnameChatPeople = (TextView) v.findViewById(R.id.nameSurnameChatPeople);

            not_selected = (ImageView) v.findViewById(R.id.not_selected);
            selected_whith_gray = (ImageView) v.findViewById(R.id.selected_whith_gray);
            selected_green = (ImageView) v.findViewById(R.id.selected_green);


        }
    }


    private void checkSelectedItems(int position, ViewHolder holder) {
        holder.not_selected.setVisibility(View.GONE);
        holder.selected_whith_gray.setVisibility(View.GONE);
        holder.selected_green.setVisibility(View.GONE);

        if (chatModels.get(position).isSelected() && !chatModels.get(position).isNowSelected()) {
            holder.selected_green.setVisibility(View.VISIBLE);
        } else if (chatModels.get(position).isNowSelected()) {
            holder.selected_whith_gray.setVisibility(View.VISIBLE);
        } else {
            holder.not_selected.setVisibility(View.VISIBLE);

        }

    }

    private void checkSelectedItemsAddOne(int position, ViewHolder holder) {
        holder.not_selected.setVisibility(View.GONE);
        holder.selected_whith_gray.setVisibility(View.GONE);
        holder.selected_green.setVisibility(View.GONE);

        if (chatModels.get(position).isNowSelected()) {
            holder.selected_whith_gray.setVisibility(View.VISIBLE);
        } else {
            holder.not_selected.setVisibility(View.VISIBLE);

        }
    }




}