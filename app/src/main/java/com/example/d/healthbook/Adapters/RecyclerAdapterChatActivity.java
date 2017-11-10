package com.example.d.healthbook.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.d.healthbook.Models.ChatModel;
import com.example.d.healthbook.Models.FeedDocument;
import com.example.d.healthbook.R;
import com.example.d.healthbook.VewHolders.ViewHolderChatNoType;
import com.example.d.healthbook.VewHolders.ViewHolderChatWhithType;
import com.example.d.healthbook.VewHolders.ViewHolderFeedNoType;
import com.example.d.healthbook.VewHolders.ViewHolderFeedWhithType;

import java.util.List;

/**
 * Created by D on 28.06.2017.
 */

public class RecyclerAdapterChatActivity  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG1 = "MY LIST ADAPTER";
    private Context context;
    private List<ChatModel> documents;
    private final int NO_TYPE = 0, WHITH_TYPE = 1;

    public RecyclerAdapterChatActivity(List<ChatModel> documentsUrl, Context context) {
        documents = documentsUrl;
        this.context = context;

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case NO_TYPE:
                View v1 = inflater.inflate(R.layout.row_chat_item_come, parent, false);
                viewHolder = new ViewHolderChatNoType(v1);
                break;
            case WHITH_TYPE:
                View v2 = inflater.inflate(R.layout.row_chat_item_sent, parent, false);
                viewHolder = new ViewHolderChatWhithType(v2);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        switch (holder.getItemViewType()) {
            case NO_TYPE:
                ViewHolderChatNoType vh1 = (ViewHolderChatNoType) holder;
                configureViewHolder1(vh1, position);
                break;
            case WHITH_TYPE:
                ViewHolderChatWhithType vh2 = (ViewHolderChatWhithType) holder;
                configureViewHolder2(vh2, position);
                break;

        }


    }

    private void configureViewHolder2(ViewHolderChatWhithType vh2, int position) {

        vh2.getTexMessage().setText(documents.get(position).getTextMessage());

        vh2.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void configureViewHolder1(ViewHolderChatNoType vh1, int position) {
        ChatModel chatModel = (ChatModel) documents.get(position);
        if (chatModel != null) {

            vh1.getTexMessage().setText(documents.get(position).getTextMessage());
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
        if (documents.get(position).getType() == 1) {
            return WHITH_TYPE;
        } else if (documents.get(position).getType() == 2) {
            return NO_TYPE;
        }
        return -1;
    }

    @Override
    public int getItemCount() {
        return documents.size();
    }
}

