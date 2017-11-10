package com.example.d.healthbook.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.d.healthbook.Activities.DoctorActivityInfo;
import com.example.d.healthbook.Activities.VisitInfoActivity;
import com.example.d.healthbook.Controller.MainController;
import com.example.d.healthbook.Models.Document;
import com.example.d.healthbook.Models.ResponseVisit;
import com.example.d.healthbook.R;

import java.util.List;

/**
 * Created by D on 20.06.2017.
 */

public class RecyclerAdapterVisit extends RecyclerView.Adapter<RecyclerAdapterVisit.ViewHolder> {
    private static final String TAG1 = "MY LIST ADAPTER";
    private Context context;
    private List<ResponseVisit> documents;
    private static final int TYPE_FOOTER = 2;

    public RecyclerAdapterVisit(List<ResponseVisit> documentsUrl, Context context) {
        documents = documentsUrl;
        this.context = context;

    }


    @Override
    public RecyclerAdapterVisit.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_visit_list, parent, false);

        return new RecyclerAdapterVisit.ViewHolder((View) view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
//        String timeVisit
        String[] split = documents.get(position).getStartDate().split(" ");
        holder.timeTVVisit.setText(split[1]);
        holder.dateTVVisit.setText(split[0]);
        holder.nameDoctorTVVisit.setText(documents.get(position).getExpertName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, VisitInfoActivity.class);
                intent.putExtra("id",documents.get(position).getExpertId());
                intent.putExtra("name",documents.get(position).getExpertName());
                intent.putExtra("start",documents.get(position).getStartDate());
                intent.putExtra("visit",documents.get(position).getVisitDate());
                intent.putExtra("end",documents.get(position).getFinishDate());
                intent.putExtra("status",documents.get(position).getStatus());

                context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return documents.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView timeTVVisit;
        Button buttonSeeVisit;
        TextView dateTVVisit;
        TextView nameDoctorTVVisit;


        public ViewHolder(final View v) {
            super(v);
            timeTVVisit = (TextView) v.findViewById(R.id.timeTVVisit);
            dateTVVisit = (TextView) v.findViewById(R.id.dateTVVisit);
            nameDoctorTVVisit = (TextView) v.findViewById(R.id.nameDoctorTVVisit);
            buttonSeeVisit=(Button)v.findViewById(R.id.buttonSeeVisit);
        }
    }

}
