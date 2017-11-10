package com.example.d.healthbook.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.d.healthbook.Models.MissionNameViewHolder;
import com.example.d.healthbook.Models.ModelWhithTasks;
import com.example.d.healthbook.Models.Task;
import com.example.d.healthbook.Models.TasksViewholder;
import com.example.d.healthbook.R;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

/**
 * Created by D on 14.07.2017.
 */

public class RecyclerAdapterProgressUserExpandable extends ExpandableRecyclerViewAdapter<MissionNameViewHolder, TasksViewholder> {

    private LayoutInflater mInflater;

//    public RecyclerAdapterProgressUserExpandable(Context context, @NonNull List<ModelWhithTasks> recipeList) {
//        super(recipeList);
//
//        mInflater = LayoutInflater.from(context);
//    }
    public RecyclerAdapterProgressUserExpandable(Context context,List<? extends ExpandableGroup> groups) {
        super(groups);
        mInflater = LayoutInflater.from(context);
    }


    @Override
    public MissionNameViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View recipeView = mInflater.inflate(R.layout.row_item_user_progress_show_tv, parent, false);
        return new MissionNameViewHolder(recipeView);

    }

    @Override
    public TasksViewholder onCreateChildViewHolder(@NonNull ViewGroup childViewGroup, int viewType) {
        View ingredientView = mInflater.inflate(R.layout.row_item_user_progress_show, childViewGroup, false);
        return new TasksViewholder(ingredientView);
    }

    @Override
    public void onBindChildViewHolder(TasksViewholder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        final Task task = (Task) group.getItems().get(childIndex);
        holder.bind(task);

    }

    @Override
    public void onBindGroupViewHolder(MissionNameViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.setTitle((ModelWhithTasks) group);


    }
}

// onBind ...
//    @Override
//    public void onBindParentViewHolder(@NonNull MissionNameViewHolder recipeViewHolder, int parentPosition, @NonNull ModelWhithTasks recipe) {
//        recipeViewHolder.bind(recipe);
//    }
//
//    @Override
//    public void onBindChildViewHolder(@NonNull TasksViewholder ingredientViewHolder, int parentPosition, int childPosition, @NonNull Task ingredient) {
//        ingredientViewHolder.bind(ingredient);
//    }
//}