package com.example.d.healthbook.CalendarBekarysa.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.percent.PercentRelativeLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.d.healthbook.CalendarBekarysa.Models.CalendarModel;
import com.example.d.healthbook.CalendarBekarysa.Views.ICalendarView;
import com.example.d.healthbook.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;


/**
 * Created by User on 06.07.2017.
 */

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.ViewHolder> {

    SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
    private List<CalendarModel> list;
    private Context context;
    private ICalendarView viewInterface;


    public CalendarModel getSelected_date() {
        return selected_date;
    }

    public void setSelected_date(CalendarModel selected_date) {
        this.selected_date = selected_date;
    }

    private CalendarModel selected_date;

    public CalendarAdapter(Context context, List<CalendarModel> list, ICalendarView viewInterface) {
        this.context = context;
        this.list = (list);
        this.viewInterface = viewInterface;
        for (CalendarModel tmp : list) {
            if (tmp.getDay() == 1) {
                this.selected_date = tmp;
            }
        }
    }
    /* metodo per filtrare la RV, esempio di uso */

    @Override
    public CalendarAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(R.layout.layout_calendar_date, parent, false);
        return new ViewHolder(row);

    }

    TextView selected_TV;

    @Override
    public void onBindViewHolder(final CalendarAdapter.ViewHolder viewHolder, final int position) {
        if (list.get(position).getDay() == 0) {
            viewHolder.date.setText("");
            viewHolder.selected_date_PRL.setVisibility(View.INVISIBLE);
            viewHolder.ovulation_mark_solid_red.setVisibility(View.GONE);
            viewHolder.ovulation_mark_PRL.setVisibility(View.GONE);
            viewHolder.event_mark_PRL.setVisibility(View.GONE);
            viewHolder.drugs_mark_PRL.setVisibility(View.GONE);
            return;
        }

        viewHolder.selected_date_PRL.setVisibility(View.INVISIBLE);
        viewHolder.ovulation_mark_solid_red.setVisibility(View.GONE);
        viewHolder.ovulation_mark_PRL.setVisibility(View.GONE);
        viewHolder.event_mark_PRL.setVisibility(View.GONE);
        viewHolder.drugs_mark_PRL.setVisibility(View.GONE);


        if (list.get(position).isNote()) {
            viewHolder.event_mark_PRL.setVisibility(View.VISIBLE);
        }
        if (list.get(position).isDrugs()) {
            viewHolder.drugs_mark_PRL.setVisibility(View.VISIBLE);
        }


        viewHolder.main_item_fl.setTag(list.get(position));
        if (selected_date == list.get(position)) {
            viewHolder.date.setTextColor(Color.parseColor("#59b538"));
            selected_TV = viewHolder.date;
        } else {
            viewHolder.date.setTextColor(Color.BLACK);
        }


        viewHolder.date.setText(list.get(position).getDay().toString());


        viewHolder.main_item_fl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected_date = (CalendarModel) view.getTag();

//                viewHolder.ovulation_mark_solid_red.setVisibility(View.VISIBLE);

                selected_TV.setTextColor(Color.BLACK);
                selected_TV = (TextView) view.findViewById(R.id.date_item_TV);
                selected_TV.setTextColor(Color.parseColor("#59b538"));
                viewInterface.onDateSelected((CalendarModel) view.getTag());
                viewInterface.onDateSelected(
                        getCalendarByDate(
                                (
                                        (CalendarModel) view.getTag()
                                ).getDate()
                        )
                );
            }
        });

    }

    public Calendar getCalendarByDate(String date) {
        boolean isSuccessfull = false;
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(format1.parse(date));
            isSuccessfull = true;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (isSuccessfull)
            return cal;
        return null;
    }

    @Override
    public int getItemCount() {

        return list != null ? list.size() : 0;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public PercentRelativeLayout event_mark_PRL;
        public PercentRelativeLayout ovulation_mark_PRL;
        public PercentRelativeLayout ovulation_mark_solid_red;
        public PercentRelativeLayout drugs_mark_PRL;
        public TextView date;
        public PercentRelativeLayout selected_date_PRL;
        public FrameLayout main_item_fl;

        //        public ImageView selected_date_IV;
        public ViewHolder(View itemView) {
            super(itemView);
            date = (TextView) itemView.findViewById(R.id.date_item_TV);
            selected_date_PRL = (PercentRelativeLayout) itemView.findViewById(R.id.selected_date_PRL);
            event_mark_PRL = (PercentRelativeLayout) itemView.findViewById(R.id.event_mark_PRL);
            ovulation_mark_PRL = (PercentRelativeLayout) itemView.findViewById(R.id.ovulation_mark_PRL);
            ovulation_mark_solid_red = (PercentRelativeLayout) itemView.findViewById(R.id.ovulation_mark_solid_red);
            drugs_mark_PRL = (PercentRelativeLayout) itemView.findViewById(R.id.drugs_mark_PRL);
            main_item_fl = (FrameLayout) itemView.findViewById(R.id.main_item_fl);
            //  selected_date_IV = (ImageView)  itemView.findViewById(R.id.selected_date_IV);

        }

    }
}