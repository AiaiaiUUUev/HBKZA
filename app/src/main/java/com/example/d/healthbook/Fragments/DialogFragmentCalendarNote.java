package com.example.d.healthbook.Fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.d.healthbook.Appi.API_Controller;
import com.example.d.healthbook.Appi.App;
import com.example.d.healthbook.CalendarBekarysa.CalendarActivity;
import com.example.d.healthbook.GlobalVariables.GlobalVariables;
import com.example.d.healthbook.Models.Event_Data;
import com.example.d.healthbook.Models.ResponseNoteType1;
import com.example.d.healthbook.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by D on 29.06.2017.
 */

public class DialogFragmentCalendarNote extends DialogFragment implements View.OnClickListener {

    final String LOG_TAG = "myLogs";
    private String editTextTitleStr = "";
    private String editTextDescriptionStr = "";
    private EditText editTextTitle;
    private EditText editTextDescription;
    private String date;
    private ResponseNoteType1 registeredUser;
    private Event_Data event_data;
    private Event_Data event_dataAll;
    private int position;
    private boolean edit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            date = getArguments().getString("date");
            event_data = (Event_Data) getArguments().getSerializable("doc");
            event_dataAll = (Event_Data) getArguments().getSerializable("docALL");
            edit = getArguments().getBoolean("edit", false);
            position = getArguments().getInt("pos");
        }


    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().setTitle("Title!");
        View v = inflater.inflate(R.layout.dialog_fragment_calendar, null);

        v.findViewById(R.id.clickBtnSaveEvent).setOnClickListener(this);
        getDialog().setCancelable(true);

        editTextTitle = (EditText) v.findViewById(R.id.titleETcalendar);
        editTextDescription = (EditText) v.findViewById(R.id.descriptionETcalendar);


        return v;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (getArguments() != null) {
            if (edit) {
                loadOldData();
            }

        }
    }

    private void loadOldData() {
        editTextTitle.setText(event_data.getTitle());
        editTextDescription.setText(event_data.getDescription());
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.clickBtnSaveEvent: {

                editTextTitleStr = editTextTitle.getText().toString();
                editTextDescriptionStr = editTextDescription.getText().toString();

                if (editTextTitleStr.equals("") && editTextDescriptionStr.equals("")) {
                    dismiss();
                } else if (!edit) {
                    saveNoteType1("1", editTextTitleStr, editTextDescriptionStr, date);
                } else {

                    updateEvent(event_data.getId(), "1", editTextTitleStr, editTextDescriptionStr, date);
                }

                break;
            }

        }


    }

    public void saveNoteType1(String type, String title, String text, String date) {
        //create Model
        ((CalendarActivity) getActivity()).sendNewEvent(type, title, text, date);
        dismiss();
    }


    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        Log.d(LOG_TAG, "Dialog 1: onDismiss");
    }

    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        dismiss();
        Log.d(LOG_TAG, "Dialog 1: onCancel");
    }


    public void updateEvent(String id, String type, String title, String text, String date) {
        App.getApi().updateNote(GlobalVariables.user_auth_token, id,
                API_Controller.saveNoteType1Json(type, title, text, date)).enqueue(new Callback<ResponseNoteType1>() {
            @Override
            public void onResponse(Call<ResponseNoteType1> call, Response<ResponseNoteType1> response) {

                if (response.code() == 200) {

                    event_data.setTitle(editTextTitleStr);
                    event_data.setDescription(editTextDescriptionStr);
                    event_dataAll.setTitle(editTextTitleStr);
                    event_dataAll.setDescription(editTextDescriptionStr);

                    ((CalendarActivity) getActivity()).adapterCalendar.notifyItemChanged(position);
                    ((CalendarActivity) getActivity()).adapterCalendar.notifyItemRangeChanged(position,
                            ((CalendarActivity) getActivity()).adapterCalendar.getItemCount());
                    dismiss();
                } else {
                }

            }

            @Override
            public void onFailure(Call<ResponseNoteType1> call, Throwable t) {
                Toast.makeText(getActivity(), "An error occurred during networking", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
