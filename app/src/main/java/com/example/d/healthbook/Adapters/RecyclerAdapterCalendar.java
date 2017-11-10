package com.example.d.healthbook.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.d.healthbook.CalendarBekarysa.CalendarActivity;
import com.example.d.healthbook.Models.CalendarModelRecycler;
import com.example.d.healthbook.Models.Event_Data;
import com.example.d.healthbook.R;
import com.example.d.healthbook.UI.CompactCalendarView.domain.Event;
import com.example.d.healthbook.VewHolders.ViewHolder1;
import com.example.d.healthbook.VewHolders.ViewHolder2;

import java.util.Calendar;
import java.util.List;

/**
 * Created by pc on 23.06.2017.
 */

public class RecyclerAdapterCalendar extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG1 = "MY LIST ADAPTER";
    private Context context;
    private List<Event_Data> documents;
    private static final int TYPE_FOOTER = 2;
    private final int ROW_GREEN = 0, ROW_GRAY = 1;

    public RecyclerAdapterCalendar(List<Event_Data> documentsUrl, Context context) {
        documents = documentsUrl;
        this.context = context;

    }

    public RecyclerAdapterCalendar() {

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case ROW_GREEN:
                View v1 = inflater.inflate(R.layout.row_item_calendar, parent, false);
                viewHolder = new ViewHolder1(v1);
                break;
            case ROW_GRAY:
                View v2 = inflater.inflate(R.layout.row_item_calendar_gray, parent, false);
                viewHolder = new ViewHolder2(v2);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        switch (holder.getItemViewType()) {
            case ROW_GREEN:
                ViewHolder1 vh1 = (ViewHolder1) holder;
                configureViewHolder1(vh1, position);
                break;
            case ROW_GRAY:
                ViewHolder2 vh2 = (ViewHolder2) holder;
                configureViewHolder2(vh2, position);
                break;


        }


    }

    private void configureViewHolder2(ViewHolder2 vh2, final int position) {
        vh2.getEventCalendargray().setText(documents.get(position).getTitle());
        vh2.getDescription_event().setText(documents.get(position).getDescription());

        vh2.getEditNote().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((CalendarActivity)context).showEditNoteDialog(true,documents.get(position),position);
            }
        });

        vh2.getDeleteNote().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Вы действительно хотите удалить заметку ")
                        .setCancelable(false)
                        .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                            public void onClick(final DialogInterface dialog, final int id) {
                                ((CalendarActivity)context).deleteNote(documents.get(position).getId(),position);
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

    private void configureViewHolder1(ViewHolder1 vh1, final int position) {
        Event_Data event = (Event_Data) documents.get(position);
        if (event != null) {
            vh1.getTitleGarmones().setText(event.getTitle());
            vh1.getTimeCheckGarmones().setText(event.getTimes());
            vh1.getEventCalendar().setText(event.getBody());

            vh1.getEditDrug().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((CalendarActivity)context).showEditDrugDialog(true,documents.get(position),position);
                }
            });
            vh1.getDeleteDrug().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage("Вы действительно хотите удалить заметку ")
                            .setCancelable(false)
                            .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                                public void onClick(final DialogInterface dialog, final int id) {
                                    ((CalendarActivity)context).deleteNote(documents.get(position).getId(),position);
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
//            vh1.getEventCalendar().setText(calendarModelRecycler.getComments());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (documents.get(position).getType()==2) {
            return ROW_GREEN;
        } else if (documents.get(position).getType()==1) {
            return ROW_GRAY;
        }
        return -1;
    }

    @Override
    public int getItemCount() {
        return documents.size();
    }
}
