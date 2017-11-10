package com.example.d.healthbook.Activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.d.healthbook.Adapters.RecyclerAdapterChannel;
import com.example.d.healthbook.Appi.API_Controller;
import com.example.d.healthbook.Appi.App;
import com.example.d.healthbook.GlobalVariables.GlobalVariables;
import com.example.d.healthbook.Models.ResponseChannel;
import com.example.d.healthbook.Models.ResponseSubscribe;
import com.example.d.healthbook.R;
import com.example.d.healthbook.UI.MyToolbar;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ChannelInfoActivity extends AppCompatActivity {
    @BindView(R.id.titleChannelInfo)
    TextView titleChannelInfo;
    @BindView(R.id.subTitleChannelInfo)
    TextView subTitleChannelInfo;
    private MyToolbar toolbar;
    @BindView(R.id.subscribeBtn)
    Button subscribeBtn;
    private ResponseSubscribe registeredUser;
    private String id;


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel_info);
        ButterKnife.bind(this);

        toolbar = (MyToolbar) findViewById(R.id.main_page_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        String title = getIntent().getStringExtra("title");
        String subtitle = getIntent().getStringExtra("subtitle");
        id = getIntent().getStringExtra("id");
        titleChannelInfo.setText(title);
        subTitleChannelInfo.setText(subtitle);


        subscribeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subscribeMethod(2, id);
            }
        });
    }

    public void subscribeMethod(Integer type, String id) {
        App.getApi().subscribe( GlobalVariables.user_auth_token,API_Controller.subscribeJson(type, id)).enqueue(new Callback<ResponseSubscribe>() {
            @Override
            public void onResponse(Call<ResponseSubscribe> call, Response<ResponseSubscribe> response) {
                int s = response.code();
                if (response.errorBody() != null) {
                    try {
                        Log.e("LOG", "Retrofit Response: " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                registeredUser = response.body();


                Toast.makeText(ChannelInfoActivity.this, "Усппешно подписались", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<ResponseSubscribe> call, Throwable t) {
                Toast.makeText(ChannelInfoActivity.this, "An error occurred during networking", Toast.LENGTH_SHORT).show();
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
