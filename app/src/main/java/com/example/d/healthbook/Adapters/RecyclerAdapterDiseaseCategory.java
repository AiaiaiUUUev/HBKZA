package com.example.d.healthbook.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.d.healthbook.Activities.DiseaseCategoryActivity;
import com.example.d.healthbook.Appi.App;
import com.example.d.healthbook.Fragments.FragmentShowInfoCategory;
import com.example.d.healthbook.Models.DocumentDiseaseCategory;
import com.example.d.healthbook.R;


import java.util.List;

/**
 * Created by D on 19.06.2017.
 */

public class RecyclerAdapterDiseaseCategory extends RecyclerView.Adapter<RecyclerAdapterDiseaseCategory.ViewHolder> {

    private Context context;
    private List<DocumentDiseaseCategory> documentDiseaseCategories;


    public RecyclerAdapterDiseaseCategory(List<DocumentDiseaseCategory> documentProtocols, Context context) {
        this.documentDiseaseCategories = documentProtocols;
        this.context = context;

    }


    @Override
    public RecyclerAdapterDiseaseCategory.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_disease_category, parent, false);


        return new RecyclerAdapterDiseaseCategory.ViewHolder((View) view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.diseaseTV.setText(documentDiseaseCategories.get(position).getNameRu());
        holder.previewDiseaseTV.setText(documentDiseaseCategories.get(position).getPreviewRu());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("body_ru", documentDiseaseCategories.get(position).getBodyRu());
                bundle.putString("title", documentDiseaseCategories.get(position).getNameRu());
                App.getInstance().setFragment((DiseaseCategoryActivity) context, FragmentShowInfoCategory.class.getName(),
                        android.R.anim.fade_in, android.R.anim.fade_out, false, bundle, false);
                DiseaseCategoryActivity.backPressed = true;
            }
        });
    }


    @Override
    public int getItemCount() {
        return documentDiseaseCategories.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView diseaseTV;
        TextView previewDiseaseTV;


        public ViewHolder(final View v) {
            super(v);

            diseaseTV = (TextView) v.findViewById(R.id.diseaseTV);
            previewDiseaseTV = (TextView) v.findViewById(R.id.previewDiseaseTV);
        }
    }

}
