package com.example.d.healthbook.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.d.healthbook.Activities.DrugsCategoryActivity;
import com.example.d.healthbook.Activities.MainPageActivity;
import com.example.d.healthbook.Appi.App;
import com.example.d.healthbook.Fragments.FragmentShowInfoCategory;
import com.example.d.healthbook.Fragments.MainPageFragment;
import com.example.d.healthbook.GlobalVariables.GlobalVariables;
import com.example.d.healthbook.Models.DocumentDrugsCategory;
import com.example.d.healthbook.R;

import java.util.List;

/**
 * Created by D on 19.06.2017.
 */

public class RecyclerAdapterDrugsCategory extends RecyclerView.Adapter<RecyclerAdapterDrugsCategory.ViewHolder> {

    private Context context;
    private List<DocumentDrugsCategory> documentDrugsCategories;


    public RecyclerAdapterDrugsCategory(List<DocumentDrugsCategory> documentProtocols, Context context) {
        this.documentDrugsCategories = documentProtocols;
        this.context = context;

    }


    @Override
    public RecyclerAdapterDrugsCategory.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_drugs_category, parent, false);


        return new RecyclerAdapterDrugsCategory.ViewHolder((View) view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.drugTV.setText(documentDrugsCategories.get(position).getNameRu());
        holder.previewDrug.setText(documentDrugsCategories.get(position).getPreviewRu());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("body_ru", documentDrugsCategories.get(position).getBodyRu());
                bundle.putString("title", documentDrugsCategories.get(position).getNameRu());
                App.getInstance().setFragment((DrugsCategoryActivity) context, FragmentShowInfoCategory.class.getName(),
                        android.R.anim.fade_in, android.R.anim.fade_out, false, bundle, false);
                DrugsCategoryActivity.backPressed=true;
            }
        });
    }


    @Override
    public int getItemCount() {
        return documentDrugsCategories.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView drugTV;
        TextView previewDrug;


        public ViewHolder(final View v) {
            super(v);

            drugTV = (TextView) v.findViewById(R.id.drugsTV);
            previewDrug = (TextView) v.findViewById(R.id.previewDrugTV);
        }
    }

}

