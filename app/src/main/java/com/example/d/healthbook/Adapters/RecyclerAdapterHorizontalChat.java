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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.d.healthbook.Activities.DoctorActivityInfo;
import com.example.d.healthbook.Controller.MainController;
import com.example.d.healthbook.Models.ChatManIMG;
import com.example.d.healthbook.Models.Document;
import com.example.d.healthbook.R;

import java.util.List;

/**
 * Created by D on 20.07.2017.
 */

public class RecyclerAdapterHorizontalChat extends RecyclerView.Adapter<RecyclerAdapterHorizontalChat.ViewHolder> {
    private static final String TAG1 = "MY LIST ADAPTER";
    private Context context;
    private List<ChatManIMG> documents;

    public RecyclerAdapterHorizontalChat(List<ChatManIMG> documentsUrl, Context context) {
        documents = documentsUrl;
        this.context = context;

    }


    @Override
    public RecyclerAdapterHorizontalChat.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_man_add_group_horizontal, parent, false);

        return new RecyclerAdapterHorizontalChat.ViewHolder((View) view);
    }

    @Override
    public void onBindViewHolder(final RecyclerAdapterHorizontalChat.ViewHolder holder, final int position) {

//        holder.imageChatMan.setImageBitmap(
//                MainController.decodeSampledBitmapFromResource(context.getResources(), documents.get(position).getDrawableIMG(),
//                        100, 100));

        holder.imageChatMan.setImageBitmap(
                getBitmapFromResources(context.getResources(), documents.get(position).getDrawableIMG()));



    }

    @Override
    public int getItemCount() {
        return documents.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageChatMan;


        public ViewHolder(final View v) {
            super(v);
            imageChatMan = (ImageView) v.findViewById(R.id.imageChatMan);


        }
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



}


