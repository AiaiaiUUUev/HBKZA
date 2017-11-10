package com.example.d.healthbook.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.d.healthbook.FragmentsTab.TabUserInfoMyFamily;
import com.example.d.healthbook.Models.DocumentMyFamily;
import com.example.d.healthbook.R;

import java.util.List;

/**
 * Created by D on 05.07.2017.
 */

public class RecyclerAdapterMyFamilyMembers extends RecyclerView.Adapter<RecyclerAdapterMyFamilyMembers.ViewHolder> {

    private Context context;
    private List<DocumentMyFamily> myFamilyMemberses;
    private TabUserInfoMyFamily fragment;


    public RecyclerAdapterMyFamilyMembers(List<DocumentMyFamily> documentProtocols, Context context, TabUserInfoMyFamily fragment) {
        this.myFamilyMemberses = documentProtocols;
        this.context = context;
        this.fragment = fragment;
    }


    @Override
    public RecyclerAdapterMyFamilyMembers.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_my_family_members, parent, false);

        return new RecyclerAdapterMyFamilyMembers.ViewHolder((View) view);
    }

    @Override
    public void onBindViewHolder(final RecyclerAdapterMyFamilyMembers.ViewHolder holder, final int position) {

        holder.name_surname_memberOfFamily.setText(myFamilyMemberses.get(position).getName());

        holder.editMemberOfFamily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragment.showEditMemberDialog(true, myFamilyMemberses.get(position).getId());
            }
        });

        holder.deleteMemberOfFamily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Вы действительно хотите удалить папку с данными ")
                        .setCancelable(false)
                        .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                            public void onClick(final DialogInterface dialog, final int id) {
                                fragment.deleteMemberOfFamily(myFamilyMemberses.get(position).getId(),
                                        holder.getAdapterPosition());
                            }
                        })
                        .setNegativeButton("Нет", new DialogInterface.OnClickListener() {
                            public void onClick(final DialogInterface dialog, final int id) {
                                dialog.cancel();
                            }
                        });


                final AlertDialog alert = builder.create();
                alert.show();
            }
        });

    }


    @Override
    public int getItemCount() {
        return myFamilyMemberses.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name_surname_memberOfFamily;
        LinearLayout editMemberOfFamily;
        LinearLayout deleteMemberOfFamily;

        public ViewHolder(final View v) {
            super(v);
            Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/Myriad Pro Regular.ttf");

            name_surname_memberOfFamily = (TextView) v.findViewById(R.id.name_surname_memberOfFamily);
            name_surname_memberOfFamily.setTypeface(font);
            editMemberOfFamily = (LinearLayout) v.findViewById(R.id.editMemberOfFamily);
            deleteMemberOfFamily = (LinearLayout) v.findViewById(R.id.deleteMemberOfFamily);
        }
    }

}

