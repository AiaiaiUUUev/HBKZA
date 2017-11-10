package com.example.d.healthbook.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.d.healthbook.Adapters.PagerAdapterDoctorInfo;
import com.example.d.healthbook.Appi.API_Controller;
import com.example.d.healthbook.Appi.App;
import com.example.d.healthbook.Controller.MainController;
import com.example.d.healthbook.GlobalVariables.GlobalVariables;
import com.example.d.healthbook.Models.ResponseAllSubscriptionsToDoctor;
import com.example.d.healthbook.Models.ResponseDoctorInfo;
import com.example.d.healthbook.Models.ResponseSubscribeToDoctor;
import com.example.d.healthbook.Models.Schedule;
import com.example.d.healthbook.R;
import com.example.d.healthbook.UI.MyToolbar;
import com.example.d.healthbook.View.DoctorInterface;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class DoctorActivityInfo extends AppCompatActivity implements DoctorInterface {
    private boolean checkSubscription = false;
    private Animation fab_open, fab_close, rotate_forward, rotate_backward, fab_open_tv, fab_close_tv;
    private MyToolbar toolbar;
    private List<Schedule> schedules;
    private ResponseDoctorInfo registeredUser;
    private String doctorID = "";
    @BindView(R.id.profile_imageINFO)
    CircleImageView profile_imageINFO;
    @BindView(R.id.name_surnameTVINFO)
    TextView name_surnameTVINFO;
    @BindView(R.id.specialityTVINFO)
    TextView specialityTVINFO;
    @BindView(R.id.experienceTVINFO)
    TextView experienceTVINFO;


    @BindView((R.id.pager))
    ViewPager viewPager;



    @BindView((R.id.main_appbar))
    AppBarLayout main_appbar;

    @BindView(R.id.toolbar_title)
    TextView toolbar_title;

    PagerAdapterDoctorInfo adapter = null;
    private String expert_Room_ID = " ";
    private ProgressDialog progressDialog = null;
    private FloatingActionButton fabSetting;
    private FloatingActionButton fabSub1;
    private FloatingActionButton fabSub2;
    private FloatingActionButton fabSub3;
    private boolean fabStatus = false;
    private ResponseSubscribeToDoctor registeredUserSubscribeToDoctor;
    private List<ResponseAllSubscriptionsToDoctor> registeredUserSubscriptions;


    void _setOn(final int color_value) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    toolbar_title.setTextColor(ContextCompat.getColor(DoctorActivityInfo.this, color_value));
                } else {
                    toolbar_title.setTextColor(getResources().getColor(color_value));
                }
            }
        });

    }

    public float convertDpToPixel(float dp) {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float px = dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_info);
        ButterKnife.bind(this);
        init_anims();
//        App.getInstance().setFragment(DoctorActivityInfo.this, DoctorActivityInfoFragment.class.getName(), android.R.anim.fade_in, android.R.anim.fade_out, true);
        toolbar = (MyToolbar) findViewById(R.id.main_page_toolbar);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_tb);
        collapsingToolbarLayout.setTitleEnabled(false);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        final int main_appbar_max_height = (int) convertDpToPixel(150);
        final int main_toolbar_max_height = toolbar.getHeight();
        main_appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0 || Math.abs(verticalOffset) <= main_appbar_max_height) {
                    _setOn(R.color.white);
                } else {
                    _setOn(R.color.bpBlue);
                }

            }
        });

        fabSetting = (FloatingActionButton) this.findViewById(R.id.fabSetting);
        fabSub1 = (FloatingActionButton) this.findViewById(R.id.fabSub1);
        fabSub2 = (FloatingActionButton) this.findViewById(R.id.fabSub2);
        fabSub3 = (FloatingActionButton) this.findViewById(R.id.fabSub3);


        /* when fab Setting (fab main) clicked */
        fabSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFAB();
            }
        });

        /* enable clik on FrameLayout fraToolFloat */


        fabSub3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                                String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

//                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                                String currentDateandTime = sdf.format(new Date());
//
//                                visitMethod(GlobalVariables.user_id, expert_Room_ID, currentDateandTime);

                Intent intent = new Intent(DoctorActivityInfo.this, ChooseVisitDataActivity.class);
                Bundle extra = new Bundle();
                extra.putSerializable("schedules", (Serializable) schedules);
                intent.putExtra("extra", extra);
                intent.putExtra("roomID", expert_Room_ID);
                intent.putExtra("id", doctorID);

                if (!expert_Room_ID.equals(" ")) {
                    startActivity(intent);
                } else {
                    Toast.makeText(DoctorActivityInfo.this, "К сожалению нет адреса работы врача", Toast.LENGTH_LONG).show();
                }


            }
        });

        fabSub2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkSubscription) {
                    subscribeToDoctor(doctorID);
                } else {
                    unSubscribeToDoctor(doctorID);
                }

            }
        });


        String id = getIntent().getStringExtra("idDoctor");
        String imageString = getIntent().getStringExtra("imageDoc");
        String name = getIntent().getStringExtra("nameDoc");
        String surname = getIntent().getStringExtra("surNameDoc");
        name_surnameTVINFO.setText(name + " " + surname);
        Glide.with(

                getApplicationContext()).

                load(imageString).

                centerCrop().

                into(profile_imageINFO);

        seeDoctorInfoMethod(id);
        seeSubscriptions();


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().

                setText("Информация"));
        tabLayout.addTab(tabLayout.newTab().

                setText("График работы"));
        tabLayout.addTab(tabLayout.newTab().

                setText("Отзывы"));
        tabLayout.addTab(tabLayout.newTab().

                setText("Статус"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        adapter = new

                PagerAdapterDoctorInfo
                (this, getSupportFragmentManager(), tabLayout.

                        getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()

        {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void unSubscribeToDoctor(String doctorID) {
        App.getApi().unSubscribeToDoctor(GlobalVariables.user_auth_token, API_Controller.subscribeToDoctorJson(doctorID)).enqueue(new Callback<ResponseSubscribeToDoctor>() {
            @Override
            public void onResponse(Call<ResponseSubscribeToDoctor> call, Response<ResponseSubscribeToDoctor> response) {

                if (response.code() == 200) {
                    registeredUserSubscribeToDoctor = response.body();
                    MainController.showPreparedToast(DoctorActivityInfo.this, "Вы успешно отписались");
                    fabSub2.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#59b538")));
                    checkSubscription = false;
                } else {
                    MainController.showPreparedToast(DoctorActivityInfo.this, "Failed");
                }

            }

            @Override
            public void onFailure(Call<ResponseSubscribeToDoctor> call, Throwable t) {
                Toast.makeText(DoctorActivityInfo.this, "An error occurred during networking", Toast.LENGTH_SHORT).show();
            }
        });
    }


    void init_anims() {
        fab_open = AnimationUtils.loadAnimation(this, R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(this, R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(this, R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(this, R.anim.rotate_backward);
        fab_open_tv = AnimationUtils.loadAnimation(this, R.anim.fab_open_tv);
        fab_close_tv = AnimationUtils.loadAnimation(this, R.anim.fab_close_tv);
    }

    public void seeDoctorInfoMethod(String id) {
        App.getApi().seedoctorInfo(id).enqueue(new Callback<ResponseDoctorInfo>() {
            @Override
            public void onResponse(Call<ResponseDoctorInfo> call, Response<ResponseDoctorInfo> response) {
                int s = response.code();
                if (response.errorBody() != null) {
                    try {
                        Log.e("LOG", "Retrofit Response: " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return;
                }
                registeredUser = response.body();
                if (response.body().getRooms().size() != 0) {
                    expert_Room_ID = response.body().getRooms().get(0).getId();
                }


                schedules = registeredUser.getSchedule();
                Toast.makeText(getApplication(), "SUCCESS", Toast.LENGTH_LONG).show();
                doctorID = registeredUser.getId();
                specialityTVINFO.setText(registeredUser.getSpecialityName());

                experienceTVINFO.setText("Опыт работы: " + registeredUser.getExperience());

                if (adapter != null)
                    adapter.onResponseSuccess(registeredUser);


            }

            @Override
            public void onFailure(Call<ResponseDoctorInfo> call, Throwable t) {
                Toast.makeText(getApplication(), "An error occurred during networking", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void subscribeToDoctor(String doctorID) {

        App.getApi().subscribeToDoctor(GlobalVariables.user_auth_token, API_Controller.subscribeToDoctorJson(doctorID)).enqueue(new Callback<ResponseSubscribeToDoctor>() {
            @Override
            public void onResponse(Call<ResponseSubscribeToDoctor> call, Response<ResponseSubscribeToDoctor> response) {

                if (response.code() == 200) {
                    registeredUserSubscribeToDoctor = response.body();
                    MainController.showPreparedToast(DoctorActivityInfo.this, "Вы успешно подписались на врача");
                    fabSub2.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                    checkSubscription = true;
                } else {
                    MainController.showPreparedToast(DoctorActivityInfo.this, "Failed");
                }

            }

            @Override
            public void onFailure(Call<ResponseSubscribeToDoctor> call, Throwable t) {
                Toast.makeText(DoctorActivityInfo.this, "An error occurred during networking", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void seeSubscriptions() {
        App.getApi().allUserSubscpriptionsToDoc(GlobalVariables.user_auth_token).enqueue(new Callback<List<ResponseAllSubscriptionsToDoctor>>() {
            @Override
            public void onResponse(Call<List<ResponseAllSubscriptionsToDoctor>> call, Response<List<ResponseAllSubscriptionsToDoctor>> response) {
                int s = response.code();
                registeredUserSubscriptions = response.body();
                for (int i = 0; i < registeredUserSubscriptions.size(); i++) {
                    if (doctorID.equals(registeredUserSubscriptions.get(i).getId())) {
//                        fabSub2.s(Color.RED);
                        fabSub2.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                        checkSubscription = true;

                    }


                }
            }

            @Override
            public void onFailure(Call<List<ResponseAllSubscriptionsToDoctor>> call, Throwable t) {
                Toast.makeText(DoctorActivityInfo.this, "An error occurred during networking", Toast.LENGTH_SHORT).show();
            }
        });
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public ResponseDoctorInfo getData() {
        if (registeredUser != null)
            return registeredUser;
        return null;
    }

    public void animateFAB() {
        if (fabStatus) {
            fabSetting.startAnimation(rotate_backward);
            fabSub1.startAnimation(fab_close);
            fabSub2.startAnimation(fab_close);
            fabSub3.startAnimation(fab_close);
            fabSub1.setClickable(false);
            fabSub2.setClickable(false);
            fabSub3.setClickable(false);

//            tvFabSubscription.setVisibility(View.INVISIBLE);
            fabStatus = false;
        } else {
            fabSetting.startAnimation(rotate_forward);
            fabSub1.startAnimation(fab_open);
            fabSub2.startAnimation(fab_open);
            fabSub3.startAnimation(fab_open);

            fabSub1.setClickable(true);
            fabSub2.setClickable(true);
            fabSub3.setClickable(true);

            fabStatus = true;
        }
    }


}
