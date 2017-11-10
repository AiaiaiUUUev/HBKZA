package com.example.d.healthbook.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.d.healthbook.Activities.MainPageActivity;
import com.example.d.healthbook.Adapters.RecyclerAdapterCalendar;
import com.example.d.healthbook.Appi.API_Controller;
import com.example.d.healthbook.Appi.App;
import com.example.d.healthbook.Controller.MainController;
import com.example.d.healthbook.GlobalVariables.GlobalVariables;
import com.example.d.healthbook.Models.ResponseNoteType1;
import com.example.d.healthbook.R;
import com.example.d.healthbook.UI.CompactCalendarView.CompactCalendarView;
import com.example.d.healthbook.UI.CompactCalendarView.domain.Event;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class CalendarFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private Calendar calendar = Calendar.getInstance(Locale.getDefault());
    private CompactCalendarView compactCalendarView;
    private FloatingActionButton floatingActionButton;
    private Date clickedDate;
    private RecyclerAdapterCalendar adapterCalendar;
    private List<Event> show_dateChecker;
    private DialogFragmentCalendarNote dialogFragmentFilterFeed;
    private String titleSetEvent = "", descriptionSetEvent = "";
    private Realm mRealm;
    private TextView current_date;
    private int typeFromDialog;
    private boolean checkClickDayOrNot = false;
    private boolean fromMainPage = false;
    private ResponseNoteType1 registeredUser;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        if (getArguments() != null) {
            titleSetEvent = getArguments().getString("title");
            typeFromDialog = getArguments().getInt("type");
            descriptionSetEvent = getArguments().getString("descrip");
            fromMainPage = getArguments().getBoolean("true");

        }


    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        App.getInstance().setCurrentFragment(this);
        App.getInstance().changeToolbarTitle((AppCompatActivity) getActivity(), "Календарь");
        mRealm = Realm.getInstance(getActivity());

        return inflater.inflate(R.layout.calendar_fragment_layout, container, false);
    }


    void updateListUI(List<Event> show_date) {
        if (show_date == null || show_date.isEmpty()) {
            if (adapterCalendar != null) {
                show_dateChecker.clear();
                show_dateChecker.addAll(show_date);
                adapterCalendar.notifyDataSetChanged();
                return;
            } else {
                return;
            }

        }
        show_dateChecker.clear();
        show_dateChecker.addAll(show_date);
        adapterCalendar.notifyDataSetChanged();


    }

//    private void setEventFromRealm() {
//        List<Event> events = new ArrayList<Event>();
//        RealmResults<EventRealmModels> eventRealmModelses = mRealm.allObjects(EventRealmModels.class);
//        if (!eventRealmModelses.isEmpty()) {
//
//            for (int i = 0; i < eventRealmModelses.size(); i++) {
//                mRealm.beginTransaction();
//                events.add(new Event(getResources().getColor(R.color.bpBlue),
//                        eventRealmModelses.get(i).getClickedDateRealm(),
//                        new Event_Data(eventRealmModelses.get(i).getType(),
//                                eventRealmModelses.get(i).getTitleEvent(),
//                                eventRealmModelses.get(i).getDescriptionEvent())));
//                mRealm.commitTransaction();
//            }
//
//            if (getArguments() != null && fromMainPage) {
//                compactCalendarView.addEvents(events);
//                compactCalendarView.setCurrentDate(clickedDate);
//                updateListUI(compactCalendarView.getEvents(clickedDate));
//            } else {
//                compactCalendarView.addEvents(events);
//                updateListUI(compactCalendarView.getEvents(GlobalVariables.clickedDateGlobal));
//                compactCalendarView.setCurrentDate(GlobalVariables.clickedDateGlobal);
//                DateFormat formatter = new SimpleDateFormat("EEE, dd MMMM");
//                String date = formatter.format(GlobalVariables.clickedDateGlobal);
//                current_date.setText(date);
//            }
//        }
//
//
//    }

//    private void setArgumentsFromDialog() {
//        if (getArguments() != null && !fromMainPage) {
//            List<Event> events = new ArrayList<Event>();
//            Event newEvent = new Event(getResources().getColor(R.color.bpBlue), GlobalVariables.clickedDateGlobal.getTime(),
//                    new Event_Data(typeFromDialog, titleSetEvent, descriptionSetEvent));
//            events.add(newEvent);
//
//            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            String date = formatter.format(GlobalVariables.clickedDateGlobal.getTime());
//            saveTakingDrugs("1", titleSetEvent, descriptionSetEvent, date);
//
//            mRealm.beginTransaction();
//            EventRealmModels eventRealmModels = mRealm.createObject(EventRealmModels.class);
//            eventRealmModels.setClickedDateRealm(GlobalVariables.clickedDateGlobal.getTime());
//            eventRealmModels.setType(typeFromDialog);
//            eventRealmModels.setTitleEvent(titleSetEvent);
//            eventRealmModels.setDescriptionEvent(descriptionSetEvent);
//            mRealm.commitTransaction();
//
//
//        }
//    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        current_date = (TextView) view.findViewById(R.id.text_field_current_date);
        compactCalendarView = (CompactCalendarView) view.findViewById(R.id.compactcalendar_view);
        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fabCalendarEvent);
//        https://guides.codepath.com/android/Heterogenous-Layouts-inside-RecyclerView

        ((MainPageActivity) getActivity()).changeImageVisibility(0);
        ((MainPageActivity) getActivity()).changeMenuItems(0);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerCalendar);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        show_dateChecker = new ArrayList<Event>();
//        adapterCalendar = new RecyclerAdapterCalendar(show_dateChecker, getActivity());
        mRecyclerView.setAdapter(adapterCalendar);

        clickedDate = new Date();


        DateFormat formatter = new SimpleDateFormat("EEE, dd MMMM");
        String date = formatter.format(Calendar.getInstance().getTime());
        current_date.setText(date);

//        setArgumentsFromDialog();
//        setEventFromRealm();


        // define a listener to receive callbacks when certain events happen.
        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                List<Event> events = compactCalendarView.getEvents(dateClicked);
                Log.d(TAG, "Day was clicked: " + dateClicked + " with events " + events);

                clickedDate = dateClicked;

                checkClickDayOrNot = true;
                if (checkClickDayOrNot) {
                    GlobalVariables.clickedDateGlobal = clickedDate;
                }

                updateListUI(compactCalendarView.getEvents(dateClicked));
                DateFormat formatter = new SimpleDateFormat("EEE, dd MMMM");
                String date = formatter.format(dateClicked);
                current_date.setText(date);


            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                Log.d(TAG, "Month was scrolled to: " + firstDayOfNewMonth);

                DateFormat formatter = new SimpleDateFormat("MMMM");
                String date = formatter.format(firstDayOfNewMonth);
                date = Character.toUpperCase(date.charAt(0)) + date.substring(1);
                App.getInstance().changeToolbarTitle((AppCompatActivity) getActivity(), date);
            }
        });


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fromMainPage && !checkClickDayOrNot) {
                    GlobalVariables.clickedDateGlobal = clickedDate;
                    fromMainPage = false;
                }
                checkClickDayOrNot = false;
                dialogFragmentFilterFeed = new DialogFragmentCalendarNote();
                dialogFragmentFilterFeed.show(((MainPageActivity) getActivity()).getSupportFragmentManager(),
                        "dialogFragmentFilterFeed");

            }
        });

    }


    private void addEvents(int month, int year) {
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDayOfMonth = calendar.getTime();
        for (int i = 0; i < 6; i++) {
            calendar.setTime(firstDayOfMonth);
            if (month > -1) {
                calendar.set(Calendar.MONTH, month);
            }
            if (year > -1) {
                calendar.set(Calendar.ERA, GregorianCalendar.AD);
                calendar.set(Calendar.YEAR, year);
            }
            calendar.add(Calendar.DATE, i);
            setToMidnight(calendar);
            long timeInMillis = calendar.getTimeInMillis();

            List<Event> events = getEvents(timeInMillis, i);

            compactCalendarView.addEvents(events);
        }
    }

    //    private List<Event> getEvents(long timeInMillis, int day) {
//        return Arrays.asList(new Event(ContextCompat.getColor(getContext(), R.color.colorAqua),
//                timeInMillis, "Event at " +new Event_Data()));
//    }
    private List<Event> getEvents(long timeInMillis, int day) {
        return Arrays.asList(new Event(ContextCompat.getColor(getContext(), R.color.colorAqua),
                timeInMillis));
    }

    private void setToMidnight(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    public void saveNoteType1(String type, String title, String text, String date) {

        App.getApi().saveNoteType1(GlobalVariables.user_auth_token,
                API_Controller.saveNoteType1Json(type, title, text, date)).enqueue(new Callback<ResponseNoteType1>() {
            @Override
            public void onResponse(Call<ResponseNoteType1> call, Response<ResponseNoteType1> response) {

                if (response.code() == 200) {
                    registeredUser = response.body();
                    MainController.showPreparedToast(getActivity(), "Success");
                } else {
                    MainController.showPreparedToast(getActivity(), "Failed");
                }

            }

            @Override
            public void onFailure(Call<ResponseNoteType1> call, Throwable t) {
                Toast.makeText(getActivity(), "An error occurred during networking", Toast.LENGTH_SHORT).show();
            }
        });
    }


}