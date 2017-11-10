package com.example.d.healthbook.Adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.d.healthbook.Appi.API_Controller;
import com.example.d.healthbook.Appi.App;
import com.example.d.healthbook.Controller.MainController;
import com.example.d.healthbook.GlobalVariables.GlobalVariables;
import com.example.d.healthbook.Models.VisitResponse;
import com.example.d.healthbook.Models.VisitWorkTimeElement;
import com.example.d.healthbook.R;
import com.example.d.healthbook.VewHolders.ViewHolderVisitData;
import com.example.d.healthbook.VewHolders.ViewHolderVisitWhite;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by D on 07.07.2017.
 */

public class RecyclerAapterChooseVisitFinal extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG1 = "MY LIST ADAPTER";
    private Context context;
    private List<VisitWorkTimeElement> documents;
    private static final int TYPE_FOOTER = 2;
    private final int ROW_GREEN = 0, ROW_WHITE = 1;

    public RecyclerAapterChooseVisitFinal(List<VisitWorkTimeElement> documentsUrl, Context context) {
        documents = documentsUrl;
        this.context = context;

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case ROW_GREEN:
                View v1 = inflater.inflate(R.layout.row_item_choose_visit, parent, false);
                viewHolder = new ViewHolderVisitData(v1);
                break;
            case ROW_WHITE:
                View v2 = inflater.inflate(R.layout.row_item_choose_visit_white, parent, false);
                viewHolder = new ViewHolderVisitWhite(v2);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        switch (holder.getItemViewType()) {
            case ROW_GREEN:
                ViewHolderVisitData vh1 = (ViewHolderVisitData) holder;
                configureViewHolder1(vh1, position);
                break;
            case ROW_WHITE:
                ViewHolderVisitWhite vh2 = (ViewHolderVisitWhite) holder;
                configureViewHolder2(vh2, position);
                break;

        }

    }

    private void configureViewHolder2(ViewHolderVisitWhite vh2, int position) {
        vh2.getTimeVisitWhite().setText(documents.get(position).getTime_of_day());
    }

    private void configureViewHolder1(final ViewHolderVisitData vh1, final int position) {
        VisitWorkTimeElement element = documents.get(position);
        if (element != null) {
            vh1.getTimeVisit().setText(documents.get(position).getTime_of_day());
            if (documents.get(position).is_open() && !documents.get(position).is_day_passed()) {
                vh1.getTimeWork().setText("Свободно");
                vh1.getCardView().setBackgroundColor(Color.parseColor("#59b538"));


            } else {
                vh1.getTimeWork().setText("Занято");
                vh1.getCardView().setBackgroundColor(Color.parseColor("#42000000"));
                vh1.getLinearLayout().setClickable(false);
            }
            if (documents.get(position).is_open() && !documents.get(position).is_day_passed()) {
                vh1.getLinearLayout().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        visitMethod(vh1, GlobalVariables.user_id, documents.get(position).getRoomID(),
                                documents.get(position).getInfoDateAndTimeToVisit(),position);
                    }
                });
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (documents.get(position).isRangeWorkTime()) {
            return ROW_GREEN;
        } else if (!documents.get(position).isRangeWorkTime()) {
            return ROW_WHITE;
        }
        return -1;
    }

    @Override
    public int getItemCount() {
        return documents.size();
    }


    private void visitMethod(final ViewHolderVisitData holder, Integer user_id, final String expert_room_id, final String visit_date, final int position) {
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

                holder.getTimeWork().setText("Занято");
                holder.getCardView().setBackgroundColor(Color.parseColor("#42000000"));
                holder.getLinearLayout().setClickable(false);

                documents.get(position).setIs_open(false);

                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Вы успешно записались на прием, на " + visit_date)
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

