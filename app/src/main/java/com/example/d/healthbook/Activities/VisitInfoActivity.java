package com.example.d.healthbook.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.d.healthbook.Appi.App;
import com.example.d.healthbook.Controller.MainController;
import com.example.d.healthbook.Models.ResponseDoctorInfo;
import com.example.d.healthbook.R;
import com.example.d.healthbook.UI.MyToolbar;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class VisitInfoActivity extends AppCompatActivity {
    @BindView((R.id.nameDocTV))
    TextView nameDocTV;
    @BindView((R.id.experienceTV))
    TextView experienceTV;
    @BindView((R.id.titlDate))
    TextView titlDate;
    @BindView((R.id.startTime))
    TextView startTime;
    @BindView((R.id.endTime))
    TextView endTime;
    @BindView((R.id.statusTV))
    TextView statusTV;
    @BindView((R.id.profileAvaIVList))
    ImageView profileAvaIVList;
    private MyToolbar toolbar;


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_info);
        ButterKnife.bind(this);


        toolbar = (MyToolbar) findViewById(R.id.main_page_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        String id = getIntent().getStringExtra("id");
        String name = getIntent().getStringExtra("name");
        String start = getIntent().getStringExtra("start");
        String visit = getIntent().getStringExtra("visit");
        String end = getIntent().getStringExtra("end");
        String status = getIntent().getStringExtra("status");

        nameDocTV.setText(name);
        titlDate.setText("Дата: " + start);
        startTime.setText("Начало: " + visit);
        endTime.setText("Конец: " + end);
        statusTV.setText(status);
        seeDoctorInfoMethod(id);

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
                experienceTV.setText("Стаж работы: " + response.body().getExperience());
                MainController.setImageToViewById(VisitInfoActivity.this, response.body().getPhoto(), profileAvaIVList);


            }

            @Override
            public void onFailure(Call<ResponseDoctorInfo> call, Throwable t) {
                Toast.makeText(getApplication(), "An error occurred during networking", Toast.LENGTH_SHORT).show();
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
}
