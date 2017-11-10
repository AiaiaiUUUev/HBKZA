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
import com.example.d.healthbook.Activities.MainPageActivity;
import com.example.d.healthbook.Controller.MainController;
import com.example.d.healthbook.Models.Document;
import com.example.d.healthbook.Models.Mention;
import com.example.d.healthbook.R;

import java.util.List;

/**
 * Created by pc on 09.06.2017.
 */

public class RecyclerAdapterMentionsClinicInfo extends RecyclerView.Adapter<RecyclerAdapterMentionsClinicInfo.ViewHolder> {

    private Context context;
    private List<Mention> mention;


    public RecyclerAdapterMentionsClinicInfo(List<Mention> mentions, Context context) {
        mention = mentions;
        this.context = context;

    }


    @Override
    public RecyclerAdapterMentionsClinicInfo.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_mentions_clinic_info, parent, false);

        return new RecyclerAdapterMentionsClinicInfo.ViewHolder((View) view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MainController.setImageToViewById(context, mention.get(position).getPhoto(), holder.profileMentionIV);
        holder.nameProfileListMention.setText(mention.get(position).getName() + " " + mention.get(position).getSurname());
        holder.mention_info.setText(mention.get(position).getText());


    }


    @Override
    public int getItemCount() {
        return mention.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView profileMentionIV;
        TextView nameProfileListMention;
        TextView time_Mention_public_TV;
        TextView mention_info;



        public ViewHolder(final View v) {
            super(v);
            profileMentionIV = (ImageView) v.findViewById(R.id.profileMentionIV);
            nameProfileListMention = (TextView) v.findViewById(R.id.nameProfileListMention);
            time_Mention_public_TV = (TextView) v.findViewById(R.id.time_Mention_public_TV);
            mention_info = (TextView) v.findViewById(R.id.mention_info);

        }
    }

}