package com.example.d.healthbook.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.d.healthbook.Models.FeedDocument;
import com.example.d.healthbook.Models.Task;
import com.example.d.healthbook.R;
import com.example.d.healthbook.VewHolders.ViewHolderFeedNoType;
import com.example.d.healthbook.VewHolders.ViewHolderShowTasks;
import com.example.d.healthbook.VewHolders.ViewHoldersShowOnlyText;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by D on 03.07.2017.
 */

public class RecyclerAdapterProgressUser  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG1 = "MY LIST ADAPTER";
    private Context context;
    private List<Object> documents;
    private final int NO_TYPE = 0, WHITH_TYPE = 1;

    public RecyclerAdapterProgressUser(List<Object> documentsUrl, Context context) {
        documents = documentsUrl;
        this.context = context;

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case NO_TYPE:
                View v1 = inflater.inflate(R.layout.row_item_user_progress_show_tv, parent, false);
                viewHolder = new ViewHoldersShowOnlyText(v1);
                break;
            case WHITH_TYPE:
                View v2 = inflater.inflate(R.layout.row_item_user_progress_show, parent, false);
                viewHolder = new ViewHolderShowTasks(v2);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        switch (holder.getItemViewType()) {
            case NO_TYPE:
                ViewHoldersShowOnlyText vh1 = (ViewHoldersShowOnlyText) holder;
                configureViewHolder1(vh1, position);
                break;
            case WHITH_TYPE:
                ViewHolderShowTasks vh2 = (ViewHolderShowTasks) holder;
                configureViewHolder2(vh2, position);
                break;

        }


    }

    private void configureViewHolder2(ViewHolderShowTasks vh2, int position) {
        Task task = (Task) documents.get(position);
        vh2.getInfoProgressTV().setText(task.getName());
        if (task.getComplete()) {
            vh2.getProgressChBx().setChecked(true);
        } else {
            vh2.getProgressChBx().setChecked(false);
        }
    }

    private void configureViewHolder1(ViewHoldersShowOnlyText vh1, int position) {
//        FeedDocument feedDocument = (FeedDocument) documents.get(position);
        vh1.getUserProgressTVONE().setText(documents.get(position).toString());

    }

    @Override
    public int getItemViewType(int position) {
        if (documents.get(position) instanceof Task) {
            return WHITH_TYPE;
        } else if (documents.get(position) instanceof String) {
            return NO_TYPE;
        }
        return -1;
    }

    @Override
    public int getItemCount() {
        return documents.size();
    }
}
