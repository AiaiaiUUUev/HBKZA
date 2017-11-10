package com.example.d.healthbook.Activities;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.d.healthbook.Adapters.PagerAdapterClinicInfo;
import com.example.d.healthbook.Appi.App;
import com.example.d.healthbook.Models.ResponseClinicInfo2;
import com.example.d.healthbook.R;
import com.example.d.healthbook.UI.MyToolbar;
import com.example.d.healthbook.View.ClinicInterface;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by D on 09.06.2017.
 */

public class ClinicActivityInfo extends AppCompatActivity implements ClinicInterface {
    private PagerAdapterClinicInfo adapter;
    @BindView((R.id.pagerClinicInfo))
    ViewPager viewPager;
    @BindView((R.id.title_info_clinicTVClinicInfo))
    TextView title_info_clinicTVClinicInfo;
    @BindView((R.id.adress_clinic_TVClinicInfo))
    TextView adress_clinic_TVClinicInfo;
    @BindView((R.id.telephone_ClinicInfo))
    TextView telephone_ClinicInfo;
    @BindView((R.id.fabClinic))
    FloatingActionButton fab;
    private ResponseClinicInfo2 registeredUser;
    private MyToolbar toolbar;

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
        setContentView(R.layout.activity_clinic_info);
        ButterKnife.bind(this);

        toolbar = (MyToolbar) findViewById(R.id.main_page_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        String id = getIntent().getStringExtra("idClinic");
        seeClinicInfoMethod2(id);


//        CoordinatorLayout.LayoutParams p = (CoordinatorLayout.LayoutParams) fabVisitToDoctor.getLayoutParams();
//        p.setAnchorId(R.id.cardViewClinic);
//        fabVisitToDoctor.setLayoutParams(p);
//        fabVisitToDoctor.setVisibility(View.GONE);

        final int main_appbar_max_height = (int) convertDpToPixel(120);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.main_appbarClinic);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0 || Math.abs(verticalOffset) <= main_appbar_max_height) {
                    fab.show();
                } else {
                    fab.hide();

                }

            }

        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(ClinicActivityInfo.this);
                builder.setMessage("Вы хотите оставить озыв?")
                        .setCancelable(false)
                        .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                            public void onClick(final DialogInterface dialog, final int id) {

//                                String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                String currentDateandTime = sdf.format(new Date());


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


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layoutClinicInfo);
        tabLayout.addTab(tabLayout.newTab().setText("Информация"));
        tabLayout.addTab(tabLayout.newTab().setText("Специалисты"));
        tabLayout.addTab(tabLayout.newTab().setText("Отзывы"));
        tabLayout.addTab(tabLayout.newTab().setText("Статусы(0)"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        adapter = new PagerAdapterClinicInfo
                (this, getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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


    public void seeClinicInfoMethod2(String id) {
        App.getApi().seeclinicInfo2(id).enqueue(new Callback<ResponseClinicInfo2>() {
            @Override
            public void onResponse(Call<ResponseClinicInfo2> call, Response<ResponseClinicInfo2> response) {
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
//                expert_Room_ID = response.body().getRooms().get(0).getId();
                title_info_clinicTVClinicInfo.setText(registeredUser.getName());
                adress_clinic_TVClinicInfo.setText(registeredUser.getAddress());

                Toast.makeText(getApplication(), "SUCCESS", Toast.LENGTH_LONG).show();

//

                if (adapter != null)
                    adapter.onResponseSuccess(registeredUser);


            }

            @Override
            public void onFailure(Call<ResponseClinicInfo2> call, Throwable t) {
                Toast.makeText(getApplication(), "An error occurred during networking", Toast.LENGTH_SHORT).show();
            }
        });
    }

//    public void seeClinicInfoMethod(String id) {
//        App.getApi().seeclinicInfo(id).enqueue(new Callback<ResponseClinicInfo>() {
//            @Override
//            public void onResponse(Call<ResponseClinicInfo> call, Response<ResponseClinicInfo> response) {
//                int s = response.code();
//                if (response.errorBody() != null) {
//                    try {
//                        Log.e("LOG", "Retrofit Response: " + response.errorBody().string());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    return;
//                }
//                registeredUser = response.body();
////                expert_Room_ID = response.body().getRooms().get(0).getId();
//                title_info_clinicTVClinicInfo.setText(registeredUser.getName());
//                adress_clinic_TVClinicInfo.setText(registeredUser.getAddress());
//
//                Toast.makeText(getApplication(), "SUCCESS", Toast.LENGTH_LONG).show();
//
////                specialityTVINFO.setText(registeredUser.getSpecialityName());
////
////                experienceTVINFO.setText("Опыт работы: " + registeredUser.getExperience());
//
//                if (customPagerAdapter != null)
//                    customPagerAdapter.onResponseSuccess(registeredUser);
//
//
//            }
//
//            @Override
//            public void onFailure(Call<ResponseClinicInfo> call, Throwable t) {
//                Toast.makeText(getApplication(), "An error occurred during networking", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

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
    public ResponseClinicInfo2 getData() {
        if (registeredUser != null)
            return registeredUser;
        return null;
    }
}
