package com.example.d.healthbook.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.d.healthbook.Controller.MainController;
import com.example.d.healthbook.Models.ResponseAllSubscriptionsToDoctor;
import com.example.d.healthbook.R;

import java.util.List;

/**
 * Created by D on 03.07.2017.
 */

public class RecyclerAdapterAllSubscriptionToDoc extends RecyclerView.Adapter<RecyclerAdapterAllSubscriptionToDoc.ViewHolder> {

    private Context context;
    private List<ResponseAllSubscriptionsToDoctor> responseAllSubscriptionsToDoctors;


    public RecyclerAdapterAllSubscriptionToDoc(List<ResponseAllSubscriptionsToDoctor> subscriptions, Context context) {
        responseAllSubscriptionsToDoctors = subscriptions;
        this.context = context;

    }


    @Override
    public RecyclerAdapterAllSubscriptionToDoc.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_all_subscription_to_doctors, parent, false);

        return new RecyclerAdapterAllSubscriptionToDoc.ViewHolder((View) view);
    }

    @Override
    public void onBindViewHolder(RecyclerAdapterAllSubscriptionToDoc.ViewHolder holder, int position) {
        MainController.setImageToViewById(context, responseAllSubscriptionsToDoctors.get(position).getPhoto(), holder.imageDocSubscription);
        holder.nameSurnameDocSubscription.setText(responseAllSubscriptionsToDoctors.get(position).getName() + " " + responseAllSubscriptionsToDoctors.get(position).getSurname());
        holder.experienceDocSubscription.setText("Стаж работы: "+responseAllSubscriptionsToDoctors.get(position).getExperience());


    }


    @Override
    public int getItemCount() {
        return responseAllSubscriptionsToDoctors.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageDocSubscription;
        TextView nameSurnameDocSubscription;
        TextView experienceDocSubscription;




        public ViewHolder(final View v) {
            super(v);
            imageDocSubscription = (ImageView) v.findViewById(R.id.imageDocSubscription);
            nameSurnameDocSubscription = (TextView) v.findViewById(R.id.nameSurnameDocSubscription);
            experienceDocSubscription = (TextView) v.findViewById(R.id.experienceDocSubscription);

        }
    }

}