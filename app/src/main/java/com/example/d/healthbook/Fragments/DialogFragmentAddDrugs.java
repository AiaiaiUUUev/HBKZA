package com.example.d.healthbook.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.d.healthbook.Adapters.CustomMyAdapterSpinner;
import com.example.d.healthbook.Appi.API_Controller;
import com.example.d.healthbook.Appi.App;
import com.example.d.healthbook.CalendarBekarysa.CalendarActivity;
import com.example.d.healthbook.GlobalVariables.GlobalVariables;
import com.example.d.healthbook.Models.Event_Data;
import com.example.d.healthbook.Models.ResponseNoteType1;
import com.example.d.healthbook.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by D on 13.07.2017.
 */

public class DialogFragmentAddDrugs extends DialogFragment implements View.OnClickListener {

    final String LOG_TAG = "myLogs";
    private String editTextquantityDrugsETStr = "";
    private Integer editTextperiodETStr = 0;
    private Integer editTextdurationETStr = 0;
    private String editTextTitleStr = "";
    private String editTextDescriptionStr = "";
    private String admissiontimeETcalendarStr = "";
    private String admissiontimeETcalendar2Str = "";

    private Spinner quantityDrugs;
    private Spinner periodET;
    private Spinner durationET;

    private EditText editTextTitle;
    private EditText editTextDescription;

    private EditText admissiontimeETcalendar;
    private EditText admissiontimeETcalendar2;
    private String date;
    private ResponseNoteType1 registeredUser;
    private CustomMyAdapterSpinner adapter;

    private Event_Data event_data;
    private Event_Data event_dataAll;
    private int position;
    private boolean edit;


    private String[] quantityDrugArray;

    private boolean alldataEntered = false;

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
        editTextDescription.setText(event_data.getBody());

        String spit[] = event_data.getTimes().split("/");
        admissiontimeETcalendar.setText(spit[0]);
        admissiontimeETcalendar2.setText(spit[1]);


        for (int i = 0; i < quantityDrugArray.length; i++) {
            if (event_data.getPills().equals(quantityDrugArray[i])) {
                quantityDrugs.setSelection(i);
            }
        }
        for (int i = 0; i < quantityDrugArray.length; i++) {
            if (event_data.getPeriod() == Integer.parseInt(quantityDrugArray[i])) {
                periodET.setSelection(i);
            }
        }
        for (int i = 0; i < quantityDrugArray.length; i++) {
            if (event_data.getDuration() == Integer.parseInt(quantityDrugArray[i])) {
                durationET.setSelection(i);
            }
        }


    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().setTitle("Title!");
        View v = inflater.inflate(R.layout.dialog_fragment_add_drugs, null);

        v.findViewById(R.id.clickBtnSaveEvent).setOnClickListener(this);
        v.findViewById(R.id.clickBtnExitEvent).setOnClickListener(this);

        editTextTitle = (EditText) v.findViewById(R.id.titleETcalendar);
        editTextDescription = (EditText) v.findViewById(R.id.descriptionETcalendar);

        admissiontimeETcalendar = (EditText) v.findViewById(R.id.admissiontimeETcalendar);
        admissiontimeETcalendar2 = (EditText) v.findViewById(R.id.admissiontimeETcalendar2);

        quantityDrugs = (Spinner) v.findViewById(R.id.quantityDrugsSpinner);
        quantityDrugArray = new String[20];
        for (int i = 0; i < 20; i++) {
            quantityDrugArray[i] = i + 1 + "";
        }


        setAdapter("Количество таблеток");

        quantityDrugs.setAdapter(adapter);

        quantityDrugs.setSelection(adapter.getCount());
        quantityDrugs.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hideKeyboard(editTextDescription);
                return false;
            }
        });


        periodET = (Spinner) v.findViewById(R.id.periodSpinner);
        setAdapter("Количество дней");
        periodET.setAdapter(adapter);
        periodET.setSelection(adapter.getCount());

        periodET.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hideKeyboard(editTextDescription);
                return false;
            }
        });


        durationET = (Spinner) v.findViewById(R.id.durationSpinner);
        setAdapter("Количество раз");
        durationET.setAdapter(adapter);
        durationET.setSelection(adapter.getCount());
        durationET.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                hideKeyboard(editTextDescription);
                return false;
            }
        });


        return v;

    }

    public void setAdapter(String text) {
        adapter = new CustomMyAdapterSpinner(getActivity(),
                R.layout.text_spinner_item_custom, quantityDrugArray,
                "This is hint displayed in spinner", text);
        adapter.setDropDownViewResource(R.layout.my_spinner_drop_down);


    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.clickBtnSaveEvent: {

                editTextTitleStr = editTextTitle.getText().toString();
                editTextDescriptionStr = editTextDescription.getText().toString();

                admissiontimeETcalendarStr = admissiontimeETcalendar.getText().toString();
                admissiontimeETcalendar2Str = admissiontimeETcalendar2.getText().toString();

                List<String> times = new ArrayList<>();
                times.add(admissiontimeETcalendarStr);
                times.add(admissiontimeETcalendar2Str);

                try {
                    editTextquantityDrugsETStr = quantityDrugs.getSelectedItem().toString();
                    editTextperiodETStr = Integer.valueOf(periodET.getSelectedItem().toString());
                    editTextdurationETStr = Integer.valueOf(durationET.getSelectedItem().toString());
                    alldataEntered = true;
                } catch (ArrayIndexOutOfBoundsException e) {
                }

                if (editTextTitleStr.equals("") && editTextDescriptionStr.equals("") || editTextTitleStr == null && editTextDescriptionStr == null) {
                    Toast.makeText(getActivity(), "Пожалуйтса введите название и описание", Toast.LENGTH_SHORT).show();
                } else if (!alldataEntered) {
                    Toast.makeText(getActivity(), "Пожалуйтса введите все данные", Toast.LENGTH_SHORT).show();
                } else if (times.get(0).equals("") || times.get(1).equals("")) {
                    Toast.makeText(getActivity(), "Пожалуйста введите время приема", Toast.LENGTH_SHORT).show();
                } else if (!edit) {
                    saveTakingDrugs("2", editTextTitleStr, editTextDescriptionStr, date,
                            editTextquantityDrugsETStr, editTextperiodETStr, editTextdurationETStr, times);

                } else {

                    updateTakingDrugs(event_data.getId(), "2", editTextTitleStr, editTextDescriptionStr, date,
                            editTextquantityDrugsETStr, editTextperiodETStr, editTextdurationETStr, times);
                }
                break;
            }
            case R.id.clickBtnExitEvent: {
                dismiss();
                break;
            }


        }


    }

    public void saveTakingDrugs(String type, String title, String text, String date, String quantityDrugs,
                                Integer period, Integer duration, List<String> times) {
        //create Model
        ((CalendarActivity) getActivity()).sendNewTakingDrugs(type, title, text, date, quantityDrugs,
                period, duration, times);
        dismiss();
    }


    public static void hideKeyboard2(Context ctx) {
        InputMethodManager inputManager = (InputMethodManager) ctx
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        // check if no view has focus:
        View v = ((Activity) ctx).getCurrentFocus();
        if (v == null)
            return;

        inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);


    }

    public void hideKeyboard(View view) {
        Context context = view.getContext();
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    public void updateTakingDrugs(String id, String type, String title, String text, String date, String quantityDrugs,
                                  Integer period, Integer duration, final List<String> times) {
        App.getApi().updateNote(GlobalVariables.user_auth_token,id,
                API_Controller.saveNewTakingDrus(type, title, text, date, times, period, duration, quantityDrugs)).enqueue(new Callback<ResponseNoteType1>() {
            @Override
            public void onResponse(Call<ResponseNoteType1> call, Response<ResponseNoteType1> response) {

                if (response.code() == 200) {

                    event_data.setTitle(editTextTitleStr);
                    event_data.setDescription(editTextDescriptionStr);
                    event_data.setTimes(times.get(0) + "/" + times.get(1));
                    event_dataAll.setTitle(editTextTitleStr);
                    event_dataAll.setTimes(times.get(0) + "/" + times.get(1));
                    event_dataAll.setDescription(editTextDescriptionStr);


                    ((CalendarActivity) getActivity()).adapterCalendar.notifyItemChanged(position);
                    ((CalendarActivity) getActivity()).adapterCalendar.notifyItemRangeChanged(position,
                            ((CalendarActivity) getActivity()).adapterCalendar.getItemCount());

                    dismiss();

                } else {
                    Toast.makeText(getActivity(), "Данные были введены не корректно", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<ResponseNoteType1> call, Throwable t) {
                Toast.makeText(getActivity(), "An error occurred during networking", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        Log.d(LOG_TAG, "Dialog 1: onDismiss");
    }

    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        Log.d(LOG_TAG, "Dialog 1: onCancel");
    }


}