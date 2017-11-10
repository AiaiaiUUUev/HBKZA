package com.example.d.healthbook.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.d.healthbook.Activities.ChannelInfoActivity;
import com.example.d.healthbook.Activities.DoctorActivityInfo;
import com.example.d.healthbook.Models.Document;
import com.example.d.healthbook.Models.ListChannel;
import com.example.d.healthbook.R;

import java.util.List;

/**
 * Created by D on 19.06.2017.
 */

public class RecyclerAdapterChannel extends RecyclerView.Adapter<RecyclerAdapterChannel.ViewHolder> {
    private static final String TAG1 = "MY LIST ADAPTER";
    private Context context;
    private List<ListChannel> documents;
    private static final int TYPE_FOOTER = 2;

    public RecyclerAdapterChannel(List<ListChannel> documentsUrl, Context context) {
        documents = documentsUrl;
        this.context = context;

    }


    @Override
    public RecyclerAdapterChannel.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_channel, parent, false);

        return new RecyclerAdapterChannel.ViewHolder((View) view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.textChannel.setText(documents.get(position).getDesc());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(context, ChannelInfoActivity.class);
                intent.putExtra("title",documents.get(position).getName());
                intent.putExtra("subtitle",documents.get(position).getDesc());
                intent.putExtra("id",documents.get(position).getId());
                context.startActivity(intent);

            }
        });


    }


    @Override
    public int getItemCount() {
        return documents.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageChannel;
        TextView textChannel;




        public ViewHolder(final View v) {
            super(v);
            imageChannel = (ImageView) v.findViewById(R.id.imageChannel);
            textChannel = (TextView) v.findViewById(R.id.textChannel);


        }
    }

}
