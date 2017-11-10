package com.example.d.healthbook.VewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.d.healthbook.R;

/**
 * Created by D on 23.06.2017.
 */

public class ViewHolder2 extends RecyclerView.ViewHolder {

    private TextView eventCalendargray;
    private TextView description_event;
    private LinearLayout editNote;
    private LinearLayout deleteNote;

    public LinearLayout getEditNote() {
        return editNote;
    }

    public void setEditNote(LinearLayout editNote) {
        this.editNote = editNote;
    }

    public LinearLayout getDeleteNote() {
        return deleteNote;
    }

    public void setDeleteNote(LinearLayout deleteNote) {
        this.deleteNote = deleteNote;
    }

    public ViewHolder2(View v) {
        super(v);
        eventCalendargray = (TextView) v.findViewById(R.id.eventCalendargray);
        description_event = (TextView) v.findViewById(R.id.description_event);
        editNote= (LinearLayout) v.findViewById(R.id.editNote);
        deleteNote= (LinearLayout) v.findViewById(R.id.deleteNote);
    }

    public TextView getEventCalendargray() {
        return eventCalendargray;
    }

    public void setEventCalendargray(TextView eventCalendargray) {
        this.eventCalendargray = eventCalendargray;
    }

    public TextView getDescription_event() {
        return description_event;
    }

    public void setDescription_event(TextView description_event) {
        this.description_event = description_event;
    }
}