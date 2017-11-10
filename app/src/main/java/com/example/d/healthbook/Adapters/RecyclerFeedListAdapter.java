package com.example.d.healthbook.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.d.healthbook.Activities.NewsActivityInfo;
import com.example.d.healthbook.Models.Document;
import com.example.d.healthbook.Models.FeedDocument;
import com.example.d.healthbook.R;

import java.util.List;

/**
 * Created by D on 15.06.2017.
 */

public class RecyclerFeedListAdapter extends RecyclerView.Adapter<RecyclerFeedListAdapter.ViewHolder> {
    private Context context;
    private List<FeedDocument> documents;


    public RecyclerFeedListAdapter(List<FeedDocument> documentsUrl, Context context) {
        documents = documentsUrl;
        this.context = context;

    }

    @Override
    public RecyclerFeedListAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_feed_item, parent, false);

        return new RecyclerFeedListAdapter.ViewHolder((View) view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (documents.get(position).getType() != null) {
            Glide.with(context).load(documents.get(position).getImageSmall()).centerCrop().into(holder.imageFeed);
        } else {
            Glide.with(context).load(documents.get(position).getPhoto()).centerCrop().into(holder.imageFeed);
        }

        if (documents.get(position).getName() != null) {
            holder.feedNameSurname.setText(documents.get(position).getName() + " " + documents.get(position).getSurname());
        } else {
            holder.feedNameSurname.setText("");
        }
        holder.feed_created_time.setText(documents.get(position).getCreatedTime());
        holder.feedText.setText(documents.get(position).getText());


    }


    @Override
    public int getItemCount() {
        return documents.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageFeed;
        TextView feedNameSurname;
        TextView feedText;
        TextView feed_created_time;


        public ViewHolder(final View v) {
            super(v);

            imageFeed = (ImageView) v.findViewById(R.id.imageFeed);
            feedNameSurname = (TextView) v.findViewById(R.id.feedNameSurname);
            feed_created_time = (TextView) v.findViewById(R.id.feed_created_time);
            feedText = (TextView) v.findViewById(R.id.feedText);
        }
    }
}
