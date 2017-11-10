package com.example.d.healthbook.CalendarBekarysa;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.d.healthbook.Activities.ChatActivityShowAllChat;
import com.example.d.healthbook.Activities.MainPageActivity;
import com.example.d.healthbook.Activities.UserActivityInfo;
import com.example.d.healthbook.Adapters.RecyclerAdapterCalendar;
import com.example.d.healthbook.Appi.API_Controller;
import com.example.d.healthbook.Appi.App;
import com.example.d.healthbook.CalendarBekarysa.Adapter.CalendarAdapter;
import com.example.d.healthbook.CalendarBekarysa.Calendar_UI.CalendarHelper;
import com.example.d.healthbook.CalendarBekarysa.Calendar_UI.CustomGridLayoutManager;
import com.example.d.healthbook.CalendarBekarysa.Models.CalendarModel;
import com.example.d.healthbook.CalendarBekarysa.Views.ICalendarView;
import com.example.d.healthbook.Controller.MainController;
import com.example.d.healthbook.Fragments.DialogFragmentAddDrugs;
import com.example.d.healthbook.Fragments.DialogFragmentCalendarNote;
import com.example.d.healthbook.Fragments.FixedHoloDatePickerDialog;
import com.example.d.healthbook.GlobalVariables.GlobalVariables;
import com.example.d.healthbook.Models.Event_Data;
import com.example.d.healthbook.Models.ResponseDeleteNote;
import com.example.d.healthbook.Models.ResponseNoteType1;
import com.example.d.healthbook.R;
import com.example.d.healthbook.UI.MyToolbar;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class CalendarActivity extends AppCompatActivity implements ICalendarView {

    private ImageButton womenCalendarBtn;
    private ImageButton addDrugBtn;
    private NavigationView navigation;
    private ActionBarDrawerToggle mToggle;
    private DrawerLayout drawerLayout;
    private MyToolbar toolbar;
    private TextView toolbar_title;
    private Intent intent;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TextView current_date;
    private RecyclerView mRecyclerView;
    private List<Event_Data> event;
    private List<Event_Data> eventAll = new ArrayList<>();
    public RecyclerAdapterCalendar adapterCalendar;
    private String stringSelectedDay = "";
    private Date dateSelected;
    private List<ResponseNoteType1> responseCalBody;
    private DialogFragmentCalendarNote dialogFragmentNote;
    private String dateStr = "";
    private DialogFragmentAddDrugs dialogFragmentAddDrugs;
    @BindView(R.id.note_btn)
    ImageButton add_note_btn;


    @Override
    public void onDateSelected(final CalendarModel out) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
//                Toast.makeText(CalendarActivity.this, out.getDate(), Toast.LENGTH_SHORT).show();
                dateStr = out.getDate();
                DateFormat formatter = new SimpleDateFormat("EEE, dd MMMM");


                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    dateSelected = sdf.parse(out.getDate());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                stringSelectedDay = formatter.format(dateSelected.getTime());
                current_date.setText(stringSelectedDay);


                updateListUI(getEventCliclkedDay(eventAll, out.getDate()));
                adapterCalendar.notifyDataSetChanged();
//                mAdapter.notifyDataSetChanged();

            }
        });
    }


    @Override
    public void onDateSelected(Calendar out) {


    }


    TextView date_info_TV;
    RecyclerView calendar_core_RV;
    Button month_before_btn;
    Button month_after_btn;
    final Calendar cal = Calendar.getInstance();
    SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
    final List<CalendarModel> calendarDatum = new ArrayList<>();
    CalendarHelper calendarHelper = new CalendarHelper();

    CalendarAdapter mAdapter;


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_main);
        ButterKnife.bind(this);

        addDrugBtn = (ImageButton) findViewById(R.id.addDrugBtn);
        womenCalendarBtn = (ImageButton) findViewById(R.id.womenCalendarBtn);

        navigation = (NavigationView) findViewById(R.id.navigationView);
        toolbar = (MyToolbar) findViewById(R.id.main_page_toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mToggle.setDrawerIndicatorEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu_black_24x24);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setText("Календарь");
        navigationClickItemListener();

        navigation.getMenu().getItem(1).setChecked(true);
        current_date = (TextView) findViewById(R.id.text_field_current_date);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerCalendar);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        event = new ArrayList<Event_Data>();
        adapterCalendar = new RecyclerAdapterCalendar(event, this);
        mRecyclerView.setAdapter(adapterCalendar);

        date_info_TV = (TextView) findViewById(R.id.date_info_TV);
        datePickerMethod();

        ClickListenerBtnToolbar();
        ClickBtnAddNote();


        calendar_core_RV = (RecyclerView) findViewById(R.id.calendar_core_RV);
        month_before_btn = (Button) findViewById(R.id.month_before_btn);
        month_after_btn = (Button) findViewById(R.id.month_after_btn);
        month_before_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        cal.add(Calendar.MONTH, -1);
                        calendarDatum.clear();
                        calendarDatum.addAll(calendarHelper.generateMonth(cal));
                        mAdapter.notifyDataSetChanged();
                        updateUI();

                        for (int i = 0; i < responseCalBody.size(); i++) {
                            checkNoteEquals(responseCalBody.get(i));
                        }
                    }
                });
            }
        });
        month_after_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        cal.add(Calendar.MONTH, 1);
                        calendarDatum.clear();
                        calendarDatum.addAll(calendarHelper.generateMonth(cal));
                        mAdapter.notifyDataSetChanged();
                        updateUI();

                        for (int i = 0; i < responseCalBody.size(); i++) {
                            checkNoteEquals(responseCalBody.get(i));
                        }

                    }
                });
            }
        });


        calendar_core_RV.setLayoutManager(new CustomGridLayoutManager(this, 7));
        calendarDatum.addAll(calendarHelper.generateMonth(cal));
        mAdapter = new CalendarAdapter(this, calendarDatum, CalendarActivity.this);
        calendar_core_RV.setAdapter(mAdapter);
        seeNote1("1");
        updateUI();

    }

    private void ClickBtnAddNote() {
        add_note_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditNoteDialog(false, new Event_Data(1, "", "", "", ""), 0);
            }
        });

    }

    public void showEditNoteDialog(boolean edit, Event_Data data, int position) {
        Bundle bundle = new Bundle();

        if (edit) {
            bundle.putSerializable("doc", data);
            bundle.putBoolean("edit", true);
            bundle.putInt("pos", position);
            for (int i = 0; i < eventAll.size(); i++) {
                if (eventAll.get(i).getId().equals(data.getId())) {
                    bundle.putSerializable("docALL", eventAll.get(i));
                }
            }
        }
        FragmentManager fm = getSupportFragmentManager();
        bundle.putString("date", dateStr);
        dialogFragmentNote = new DialogFragmentCalendarNote();
        dialogFragmentNote.setArguments(bundle);
        dialogFragmentNote.setCancelable(true);
        dialogFragmentNote.show(fm, "dialogFragmentNote");
        fm.executePendingTransactions();
    }


    private void ClickListenerBtnToolbar() {

        womenCalendarBtn = (ImageButton) findViewById(R.id.womenCalendarBtn);

        addDrugBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showEditDrugDialog(false, new Event_Data(1, "", "", "", ""), 0);


            }
        });
    }


    public void showEditDrugDialog(boolean edit, Event_Data data, int position) {
        Bundle bundle = new Bundle();

        if (edit) {
            bundle.putSerializable("doc", data);
            bundle.putBoolean("edit", true);
            bundle.putInt("pos", position);
            for (int i = 0; i < eventAll.size(); i++) {
                if (eventAll.get(i).getId().equals(data.getId())) {
                    bundle.putSerializable("docALL", eventAll.get(i));
                }
            }
        }
        bundle.putString("date", dateStr);
        dialogFragmentAddDrugs = new DialogFragmentAddDrugs();
        dialogFragmentAddDrugs.setArguments(bundle);
        dialogFragmentAddDrugs.show(getSupportFragmentManager(), "dialogFragmentAddDrugs");
    }

    public List<Event_Data> getEventCliclkedDay(List<Event_Data> data, String formatted) {
        List<Event_Data> event_datas = new ArrayList<>();

        for (int i = 0; i < data.size(); i++) {
            String splitdate[] = data.get(i).getDate().split(" ");
            if (formatted.equals(splitdate[0])) {
                event_datas.add(data.get(i));
            }

        }

        return event_datas;
    }

    void updateListUI(List<Event_Data> show_date) {
        if (show_date == null || show_date.isEmpty()) {
            if (adapterCalendar != null) {
                event.clear();
                event.addAll(show_date);
                mAdapter.notifyDataSetChanged();
                adapterCalendar.notifyDataSetChanged();


                return;
            } else {
                return;
            }

        } else {
            event.clear();
            event.addAll(show_date);
            adapterCalendar.notifyDataSetChanged();

            mAdapter.notifyDataSetChanged();
        }


    }

    public void addElement(ResponseNoteType1 calMod) {
        //responseCalBody.add(calMod);
        processEventAll(eventAll, calMod);
        checkNoteEquals(calMod);
        String formatted = "";
        if (calMod.getType() == 1)
            formatted = format1.format((MainController.getCalendarByDate(calMod.getDate(), "yyyy-MM-dd HH:mm:ss")).getTime());
        else if (calMod.getType() == 2)
            formatted = format1.format((MainController.getCalendarByDate(calMod.getDateBegin(), "yyyy-MM-dd HH:mm:ss")).getTime());
        else
            try {
                throw new Exception("Not Implemented type");
            } catch (Exception e) {
                e.printStackTrace();
            }
        updateListUI(getEventCliclkedDay(eventAll, formatted));
        mAdapter.notifyDataSetChanged();
    }


    public void seeNote1(String type) {

        App.getApi().seeNoteType1(GlobalVariables.user_auth_token).enqueue(new Callback<List<ResponseNoteType1>>() {
            @Override
            public void onResponse(Call<List<ResponseNoteType1>> call, Response<List<ResponseNoteType1>> response) {

                if (response.code() == 200) {
                    event.clear();
                    eventAll.clear();

                    responseCalBody = response.body();
                    for (int i = 0; i < response.body().size(); i++) {
                        String date = "";

                        processEventAll(eventAll, responseCalBody.get(i));

                        checkNoteEquals(responseCalBody.get(i));
                    }

                    String formatted = format1.format(cal.getTime());

                    updateListUI(getEventCliclkedDay(eventAll, formatted));
                } else {
                    MainController.showPreparedToast(CalendarActivity.this, "Failed");
                }

            }

            @Override
            public void onFailure(Call<List<ResponseNoteType1>> call, Throwable t) {
                Toast.makeText(CalendarActivity.this, "An error occurred during networking", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void processEventAll(List<Event_Data> datum, ResponseNoteType1 data) {
        switch (data.getType()) {
            case 1: {
                datum.add(create_Default_Event(data));
                break;
            }
            case 2: {
                String Time = "";
                for (int j = 0; j < data.getTimes().size(); j++) {
                    Time += data.getTimes().get(j) + "/";
                }
                datum.add(create_Drug_Event(data, Time));
                break;
            }
            default: {

            }
        }
    }

    public Event_Data create_Default_Event(ResponseNoteType1 data) {
        return new Event_Data(data.getType(),
                data.getTitle(),
                data.getText(), data.getDate(), data.getId());
    }

    public Event_Data create_Drug_Event(ResponseNoteType1 data, String Time) {
        return new Event_Data(data.getType(),
                data.getTitle(),
                data.getText(), data.getDateBegin(), Time,
                data.getBody(), data.getId(), data.getPills(), data.getPeriod(), data.getDuration());
    }

    public void checkNoteEquals(ResponseNoteType1 responseNoteType1) {
        String splitdate[];

        for (int i = 0; i < calendarDatum.size(); i++) {
            if (responseNoteType1.getDate() != null) {
                splitdate = responseNoteType1.getDate().split(" ");
            } else {
                splitdate = responseNoteType1.getDateBegin().split(" ");
            }

            if (calendarDatum.get(i).getDate().equals(splitdate[0])) {
                if (responseNoteType1.getType() == 1) {
                    calendarDatum.get(i).setNote(true);
                } else if (responseNoteType1.getType() == 2) {
                    calendarDatum.get(i).setDrugs(true);
                }

            }
        }


    }


    public void checkNoteEquals(Event_Data event_data) {
        String splitdate[];
        for (int i = 0; i < calendarDatum.size(); i++) {
            if (event_data.getDate() != null) {
                splitdate = event_data.getDate().split(" ");

                if (calendarDatum.get(i).getDate().equals(splitdate[0])) {
                    if (event_data.getType() == 1) {
                        calendarDatum.get(i).setNote(true);
                    } else if (event_data.getType() == 2) {
                        calendarDatum.get(i).setDrugs(true);
                    }
                }
            }
        }

    }


    private void datePickerMethod() {
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Toast.makeText(CalendarActivity.this, "y" + year, Toast.LENGTH_SHORT).show();
                cal.set(year, month, dayOfMonth);
                calendarDatum.clear();
                calendarDatum.addAll(calendarHelper.generateMonth(cal));
                mAdapter.notifyDataSetChanged();
                updateUI();
            }
        };


        final Context themedContext = new ContextThemeWrapper(
                this,
                android.R.style.Theme_Holo_Light_Dialog
        );

        date_info_TV.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
//                DatePickerFragment newFragment = new DatePickerFragment();
//                newFragment.show(getSupportFragmentManager(), "Date Picker");
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                final DatePickerDialog dialog = new FixedHoloDatePickerDialog(
                        themedContext,
                        mDateSetListener, year, month, day
                );
                dialog.show();

//                DatePickerDialog dialog = new DatePickerDialog(CalendarActivity.this,
//                        android.R.style.Theme_Holo_Dialog_MinWidth,mDateSetListener, year, month, day);
//                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                dialog.show();


            }
        });
    }

    void updateUI() {
        String formatted = format1.format(cal.getTime());
        date_info_TV.setText(formatted);
        DateFormat formatter = new SimpleDateFormat("EEE, dd MMMM");
        String date = formatter.format(cal.getTime());
        current_date.setText(date);

        updateListUI(getEventCliclkedDay(eventAll, formatted));
        adapterCalendar.notifyDataSetChanged();
        mAdapter.notifyDataSetChanged();
    }


    public void navigationClickItemListener() {
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setCheckable(true);
//                item.setChecked(true);
                navigation.setCheckedItem(item.getItemId());

                int id = item.getItemId();
                switch (id) {
//                    case R.id.nav_diary_indicator:
//                        Toast.makeText(getApplicationContext(), "Дневник", Toast.LENGTH_SHORT).show();
//                        break;
                    case R.id.nav_search_doctors:
                        intent = new Intent(CalendarActivity.this, MainPageActivity.class);
                        intent.putExtra("fragment", "MainPageFragment");
                        startActivity(intent);
                        finish();
                        drawerLayout.closeDrawer(Gravity.LEFT);

                        break;
                    case R.id.nav_profile:
                        Intent intent = new Intent(CalendarActivity.this, UserActivityInfo.class);
                        startActivity(intent);
                        finish();
                        break;
                    case R.id.nav_visit:
                        intent = new Intent(CalendarActivity.this, MainPageActivity.class);
                        intent.putExtra("fragment", "VisitFragment");
                        startActivity(intent);
                        finish();
                        drawerLayout.closeDrawer(Gravity.LEFT);

                        break;
                    case R.id.nav_calendar:

                        drawerLayout.closeDrawer(Gravity.LEFT);

                        break;
                    case R.id.nav_message:
//                        intent = new Intent(CalendarActivity.this, MainPageActivity.class);
//                        intent.putExtra("fragment", "ChatFragment");
//                        startActivity(intent);
//                        finish();
//                        drawerLayout.closeDrawer(Gravity.LEFT);
                        intent = new Intent(CalendarActivity.this, ChatActivityShowAllChat.class);
                        startActivity(intent);
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return mToggle.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {

        final boolean[] backBtnClicked = {false};
        if (backBtnClicked[0]) {
            super.onBackPressed();
            finish();
        } else {
            backBtnClicked[0] = true;
            Toast.makeText(getApplicationContext(), "Нажмите еще раз если хотите выйти", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    backBtnClicked[0] = false;
                }
            }, App.getInstance().BackPressTreshHold);
        }

    }

    public void sendNewEvent(String type, String title, String text, String date) {
        App.getApi().saveNoteType1(GlobalVariables.user_auth_token,
                API_Controller.saveNoteType1Json(type, title, text, date)).enqueue(new Callback<ResponseNoteType1>() {
            @Override
            public void onResponse(Call<ResponseNoteType1> call, Response<ResponseNoteType1> response) {

                if (response.code() == 200) {
                    ResponseNoteType1 registeredUser = response.body();
                    addElement(registeredUser);
                } else {
                }

            }

            @Override
            public void onFailure(Call<ResponseNoteType1> call, Throwable t) {
                Toast.makeText(CalendarActivity.this, "An error occurred during networking", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void sendNewTakingDrugs(String type, String title, String text, String date, String quantityDrugs,
                                   Integer period, Integer duration, List<String> times) {
        App.getApi().saveNoteType1(GlobalVariables.user_auth_token,
                API_Controller.saveNewTakingDrus(type, title, text, date, times, period, duration, quantityDrugs)).enqueue(new Callback<ResponseNoteType1>() {
            @Override
            public void onResponse(Call<ResponseNoteType1> call, Response<ResponseNoteType1> response) {

                if (response.code() == 200) {
                    ResponseNoteType1 registeredUser = response.body();
                    addElement(registeredUser);
                } else {
                    Toast.makeText(CalendarActivity.this, "Данные были введены не корректно", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<ResponseNoteType1> call, Throwable t) {
                Toast.makeText(CalendarActivity.this, "An error occurred during networking", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void deleteNote(final String id, final int position) {
        App.getApi().deleteNote(GlobalVariables.user_auth_token, id)
                .enqueue(new Callback<ResponseDeleteNote>() {
                    @Override
                    public void onResponse(Call<ResponseDeleteNote> call, Response<ResponseDeleteNote> response) {
                        ResponseDeleteNote registeredUser = response.body();

                        calendarDatum.clear();
                        calendarDatum.addAll(calendarHelper.generateMonth(cal));

                        if (registeredUser.getResult().equals("ok")) {
                            event.remove(position);

                            for (int i = 0; i < eventAll.size(); i++) {
                                if (eventAll.get(i).getId().equals(id)) {
                                    eventAll.remove(i);
                                } else {
                                    checkNoteEquals(eventAll.get(i));
                                }
                            }

                            mAdapter.notifyDataSetChanged();

                            adapterCalendar.notifyItemRemoved(position);
                            adapterCalendar.notifyItemRangeChanged(position, adapterCalendar.getItemCount());

                            final AlertDialog.Builder builder = new AlertDialog.Builder(CalendarActivity.this);
                            builder.setMessage("Вы успешно удалили заметку")
                                    .setCancelable(false)
                                    .setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                                        public void onClick(final DialogInterface dialog, final int id) {
                                            dialog.cancel();
                                        }
                                    });

                            final AlertDialog alert = builder.create();
                            alert.show();

                        } else {
                            Toast.makeText(CalendarActivity.this, "Поробуйте еще раз", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseDeleteNote> call, Throwable t) {
                        Toast.makeText(CalendarActivity.this, "An error occurred during networking", Toast.LENGTH_SHORT).show();
                    }
                });
    }


}
