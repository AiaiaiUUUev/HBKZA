package com.example.d.healthbook.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.d.healthbook.Models.FeedDocument;
import com.example.d.healthbook.R;

import com.example.d.healthbook.VewHolders.ViewHolderFeedNoType;
import com.example.d.healthbook.VewHolders.ViewHolderFeedWhithType;

import java.util.List;

/**
 * Created by D on 26.06.2017.
 */

public class RecyclerFeedChangeableRowAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG1 = "MY LIST ADAPTER";
    private Context context;
    private List<FeedDocument> documents;
    private static final int TYPE_FOOTER = 2;
    private final int NO_TYPE = 0, WHITH_TYPE = 1;

    public RecyclerFeedChangeableRowAdapter(List<FeedDocument> documentsUrl, Context context) {
        documents = documentsUrl;
        this.context = context;

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case NO_TYPE:
                View v1 = inflater.inflate(R.layout.row_feed_item, parent, false);
                viewHolder = new ViewHolderFeedNoType(v1);
                break;
            case WHITH_TYPE:
                View v2 = inflater.inflate(R.layout.row_feed_item_list_whith_type, parent, false);
                viewHolder = new ViewHolderFeedWhithType(v2);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        switch (holder.getItemViewType()) {
            case NO_TYPE:
                ViewHolderFeedNoType vh1 = (ViewHolderFeedNoType) holder;
                configureViewHolder1(vh1, position);
                break;
            case WHITH_TYPE:
                ViewHolderFeedWhithType vh2 = (ViewHolderFeedWhithType) holder;
                configureViewHolder2(vh2, position);
                break;

        }


    }

    private void configureViewHolder2(ViewHolderFeedWhithType vh2, int position) {
        vh2.getTitleNewsFeed().setText(documents.get(position).getTitle());
        vh2.getFeedCategoryNewsType().setText((CharSequence) documents.get(position).getChannelName() + "*" +
                documents.get(position).getCategoryName() + "*" + documents.get(position).getCreatedTime());
        vh2.getFeed_preview().setText(documents.get(position).getPreview());
        Glide.with(context).load(documents.get(position).getImageSmall()).centerCrop().into(vh2.getImageFeedWhitType());

        vh2.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void configureViewHolder1(ViewHolderFeedNoType vh1, int position) {
        FeedDocument feedDocument = (FeedDocument) documents.get(position);
        if (feedDocument != null) {
            if (documents.get(position).getName() != null) {
                vh1.getFeedNameSurname().setText(documents.get(position).getName() + " " + documents.get(position).getSurname());
            } else {
                vh1.getFeedNameSurname().setText("");
            }
            vh1.getFeed_created_time().setText(documents.get(position).getCreatedTime());
            vh1.getFeedText().setText(documents.get(position).getText());
            Glide.with(context).load(documents.get(position).getPhoto()).centerCrop().into(vh1.getImageFeed());
            vh1.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show();
                }
            });
//            vh1.getEventCalendar().setText(calendarModelRecycler.getComments());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (documents.get(position).getType() != null) {
            return WHITH_TYPE;
        } else if (documents.get(position).getType() == null) {
            return NO_TYPE;
        }
        return -1;
    }

    @Override
    public int getItemCount() {
        return documents.size();
    }
}
