package com.example.d.healthbook.VewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.d.healthbook.R;

/**
 * Created by D on 23.06.2017.
 */

public class ViewHolder1 extends RecyclerView.ViewHolder {

    public TextView getTitleGarmones() {
        return titleGarmones;
    }

    public void setTitleGarmones(TextView titleGarmones) {
        this.titleGarmones = titleGarmones;
    }

    public TextView getTimeCheckGarmones() {
        return timeCheckGarmones;
    }

    public void setTimeCheckGarmones(TextView timeCheckGarmones) {
        this.timeCheckGarmones = timeCheckGarmones;
    }

    public TextView getEventCalendar() {
        return eventCalendar;
    }

    public void setEventCalendar(TextView eventCalendar) {
        this.eventCalendar = eventCalendar;
    }

    TextView titleGarmones;
    TextView timeCheckGarmones;
    TextView eventCalendar;
    private LinearLayout editDrug;
    private LinearLayout deleteDrug;

    public LinearLayout getEditDrug() {
        return editDrug;
    }

    public void setEditDrug(LinearLayout editDrug) {
        this.editDrug = editDrug;
    }

    public LinearLayout getDeleteDrug() {
        return deleteDrug;
    }

    public void setDeleteDrug(LinearLayout deleteDrug) {
        this.deleteDrug = deleteDrug;
    }

    public ViewHolder1(View v) {
        super(v);
        titleGarmones = (TextView) v.findViewById(R.id.titleGarmones);
        timeCheckGarmones = (TextView) v.findViewById(R.id.timeCheckGarmones);
        eventCalendar = (TextView) v.findViewById(R.id.eventCalendar);
        editDrug = (LinearLayout) v.findViewById(R.id.editDrug);
        deleteDrug = (LinearLayout) v.findViewById(R.id.deleteDrug);
    }


}
