package com.example.d.healthbook.Adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.d.healthbook.Activities.ChooseVisitDataActivity;
import com.example.d.healthbook.Activities.DoctorActivityInfo;
import com.example.d.healthbook.Appi.API_Controller;
import com.example.d.healthbook.Appi.App;
import com.example.d.healthbook.Controller.MainController;
import com.example.d.healthbook.GlobalVariables.GlobalVariables;
import com.example.d.healthbook.Models.VisitResponse;
import com.example.d.healthbook.Models.VisitWorkTimeElement;
import com.example.d.healthbook.R;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by D on 23.06.2017.
 */

public class RecyclerAdapterChooseVisit extends RecyclerView.Adapter<RecyclerAdapterChooseVisit.ViewHolder> {
    private Context context;
    private List<VisitWorkTimeElement> documents;
    private boolean checkVisitSuccess = false;


    public RecyclerAdapterChooseVisit(List<VisitWorkTimeElement> documentsUrl, Context context) {
        documents = documentsUrl;
        this.context = context;

    }


    @Override
    public RecyclerAdapterChooseVisit.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_choose_visit, parent, false);

        return new RecyclerAdapterChooseVisit.ViewHolder((View) view);
    }

    @Override
    public void onBindViewHolder(final RecyclerAdapterChooseVisit.ViewHolder holder, final int position) {

        holder.timeVisit.setText(documents.get(position).getTime_of_day());
        if (documents.get(position).is_open() && !documents.get(position).is_day_passed()) {
            holder.timeWork.setText("Свободно");
            holder.cardView.setBackgroundColor(Color.parseColor("#59b538"));


        } else {
            holder.timeWork.setText("Занято");
            holder.cardView.setBackgroundColor(Color.parseColor("#42000000"));
            holder.linearLayout.setClickable(false);
        }
        if (documents.get(position).is_open() && !documents.get(position).is_day_passed()) {
            holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    visitMethod(holder, GlobalVariables.user_id, documents.get(position).getRoomID(),
                            documents.get(position).getInfoDateAndTimeToVisit());
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return documents.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView timeVisit;
        TextView timeWork;
        CardView cardView;
        LinearLayout linearLayout;


        public ViewHolder(final View v) {
            super(v);
            timeVisit = (TextView) v.findViewById(R.id.timeVisit);
            timeWork = (TextView) v.findViewById(R.id.timeWork);
            cardView = (CardView) v.findViewById(R.id.cardViewVisit);
            linearLayout = (LinearLayout) v.findViewById(R.id.chooseVisitLL);

        }
    }

    public void visitMethod(final RecyclerAdapterChooseVisit.ViewHolder holder, Integer user_id, final String expert_room_id, final String visit_date) {
        if (GlobalVariables.user_auth_token.equals("")) {
            MainController.showToast(context, "Вы не авторизованы");
            return;
        }
        final ProgressDialog progressDialog = ProgressDialog.show(context, "", "Идет оптправка данных");
        progressDialog.setCancelable(false);
        App.getApi().visitUser(GlobalVariables.user_auth_token, API_Controller.visitJson(user_id, expert_room_id, visit_date)).enqueue(new Callback<VisitResponse>() {
            @Override
            public void onResponse(Call<VisitResponse> call, Response<VisitResponse> response) {
                int s = response.code();
                if (response.errorBody() != null) {
                    try {
                        Log.e("LOG", "Retrofit Response: " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return;
                }
                progressDialog.dismiss();
//                Toast.makeText(context, "Вы успешно записались к доктору", Toast.LENGTH_LONG).show();

                holder.timeWork.setText("Занято");
                holder.cardView.setBackgroundColor(Color.parseColor("#42000000"));
                holder.linearLayout.setClickable(false);

                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Вы успешно записались на прием, на "+ visit_date)
                        .setCancelable(false)
                        .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                            public void onClick(final DialogInterface dialog, final int id) {

                                dialog.cancel();
                            }
                        });

                final AlertDialog alert = builder.create();
                alert.show();


            }

            @Override
            public void onFailure(Call<VisitResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(context, "Пожалуйста повторите попытку", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
