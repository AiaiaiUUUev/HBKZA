package com.example.d.healthbook.FragmentsTab;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.d.healthbook.Activities.UserActivityInfo;
import com.example.d.healthbook.Adapters.RecyclerAdapterMyFamilyMembers;
import com.example.d.healthbook.Appi.App;
import com.example.d.healthbook.Fragments.DialogFragmentCreateMemberOfFamily;
import com.example.d.healthbook.GlobalVariables.GlobalVariables;
import com.example.d.healthbook.Models.DocumentMyFamily;
import com.example.d.healthbook.Models.ResponseDeleteMemberData;
import com.example.d.healthbook.Models.ResponseMyFamilyMembers;
import com.example.d.healthbook.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by D on 01.07.2017.
 */

public class TabUserInfoMyFamily extends Fragment {


    private boolean isViewCreated = false;
    private ResponseMyFamilyMembers mainData;
    private List<DocumentMyFamily> documentProtocols;
    private RecyclerView mRecyclerView;
    private LinearLayout addBtn;
    private RecyclerAdapterMyFamilyMembers adapterMyFamilyMembers;
    private DialogFragmentCreateMemberOfFamily fragmentCreateMemberOfFamily;
    private ResponseDeleteMemberData deleteMemberOfFamily;


    public void upDateData(ResponseMyFamilyMembers data) {
        if (data != null) {
            mainData = data;
            setDataToViews();
        }
    }

    public void setDataToViews() {
        if (mainData != null && isViewCreated) {
            mRecyclerView.setVisibility(View.VISIBLE);
            documentProtocols = mainData.getDocuments();
            adapterMyFamilyMembers = new RecyclerAdapterMyFamilyMembers(documentProtocols, getActivity(), TabUserInfoMyFamily.this);
            mRecyclerView.setAdapter(adapterMyFamilyMembers);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tab_fragment_user_info_my_family, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_my_family);
        addBtn = (LinearLayout) view.findViewById(R.id.addMemberOfFimilyBTN);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditMemberDialog(false, "");
            }
        });
        isViewCreated = true;
        setDataToViews();

    }

    public void showEditMemberDialog(boolean edit, String id) {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        Bundle bundle = new Bundle();
        fragmentCreateMemberOfFamily = new DialogFragmentCreateMemberOfFamily();
        if (edit) {
            bundle.putString("id", id);
            fragmentCreateMemberOfFamily.setArguments(bundle);
        }
        fragmentCreateMemberOfFamily.show(fm, "fragmentCreateMemberOfFamily");
        fm.executePendingTransactions();
        fragmentCreateMemberOfFamily.getDialog().setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                ((UserActivityInfo) getActivity()).seeUserFamilyMembers();
            }
        });
    }

    public void deleteMemberOfFamily(String id, final int adapterPosition) {

        App.getApi().deleteAlldataofmember(GlobalVariables.user_auth_token, id).enqueue(new Callback<ResponseDeleteMemberData>() {
            @Override
            public void onResponse(Call<ResponseDeleteMemberData> call, Response<ResponseDeleteMemberData> response) {
                int s = response.code();

                deleteMemberOfFamily = response.body();
                documentProtocols.remove(adapterPosition);
                adapterMyFamilyMembers.notifyItemRemoved(adapterPosition);
                adapterMyFamilyMembers.notifyItemRangeChanged(adapterPosition, adapterMyFamilyMembers.getItemCount());


                if (deleteMemberOfFamily.getSuccess()) {

                    final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("Вы успешно удалили папку")
                            .setCancelable(false)
                            .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                                public void onClick(final DialogInterface dialog, final int id) {
                                    dialog.cancel();
                                }
                            });

                    final AlertDialog alert = builder.create();
                    alert.show();

                } else {
                    Toast.makeText(getActivity(), "Ошибка при загрузке ", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ResponseDeleteMemberData> call, Throwable t) {
                Toast.makeText(getActivity(), "An error occurred during networking", Toast.LENGTH_SHORT).show();
            }
        });
    }


}

