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
import com.example.d.healthbook.Activities.DoctorActivityInfo;
import com.example.d.healthbook.Models.Document;
import com.example.d.healthbook.R;

import java.util.List;

/**
 * Created by D on 07.06.2017.
 */

public class RecyclerDoctorListAdapter extends RecyclerView.Adapter<RecyclerDoctorListAdapter.ViewHolder> {
    private static final String TAG1 = "MY LIST ADAPTER";
    private Context context;
    private List<Document> documents;
    private static final int TYPE_FOOTER = 2;

    public RecyclerDoctorListAdapter(List<Document> documentsUrl, Context context) {
        documents = documentsUrl;
        this.context = context;

    }


    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_doctor_list, parent, false);

        return new ViewHolder((View) view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


        Glide.with(context).load(documents.get(position).getPhoto()).centerCrop().into(holder.profileIVList);
        holder.nameProfileList.setText(documents.get(position).getName() + " " + documents.get(position).getSurname());
        holder.specialityDocTVList.setText((CharSequence) documents.get(position).getSpecialityName());
        holder.experienceDoctorList.setText(documents.get(position).getExperience());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "CLICKED", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, DoctorActivityInfo.class);
                intent.putExtra("idDoctor", documents.get(position).getId());
//                if(documents.get(position).getRooms().size()!=0){
//                    intent.putExtra("expertRoom", documents.get(position).getRooms().get(0).getId());
//                }
                intent.putExtra("imageDoc", String.valueOf(documents.get(position).getPhoto()));
                intent.putExtra("nameDoc", String.valueOf(documents.get(position).getName()));
                intent.putExtra("surNameDoc", String.valueOf(documents.get(position).getSurname()));

                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return documents.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView profileIVList;
        TextView nameProfileList;

        TextView experienceDoctorList;
        TextView specialityDocTVList;


        public ViewHolder(final View v) {
            super(v);
            profileIVList = (ImageView) v.findViewById(R.id.profileAvaIVList);
            nameProfileList = (TextView) v.findViewById(R.id.nameProfileList);

            experienceDoctorList = (TextView) v.findViewById(R.id.experienceDoctorList);
            specialityDocTVList = (TextView) v.findViewById(R.id.specialityDocList);
        }
    }

}


