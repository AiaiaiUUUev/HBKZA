package com.example.d.healthbook.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.d.healthbook.Adapters.RecyclerAdapterProtocolCategory;
import com.example.d.healthbook.Appi.App;
import com.example.d.healthbook.Models.ResponseCategoryProtocol;
import com.example.d.healthbook.R;
import com.example.d.healthbook.UI.MyToolbar;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ProtocolCategoryActivity extends AppCompatActivity {
    private MyToolbar toolbar;
    private RecyclerView mRecyclerView;
    private RecyclerAdapterProtocolCategory mAdapter;
    private GridLayoutManager gridLayoutManager;
    @BindView(R.id.titleProtocolInfo)
    TextView titleProtocolInfo;
    private ResponseCategoryProtocol registeredUser;


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protocol_info);
        ButterKnife.bind(this);


        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewProtocolCategory);
        toolbar = (MyToolbar) findViewById(R.id.main_page_toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        String id = getIntent().getStringExtra("id");
        String title = getIntent().getStringExtra("title");
        titleProtocolInfo.setText(title + ":");
        seeProtocolCategory(id);


    }

    public void seeProtocolCategory(String id) {
        App.getApi().seeProtocolCategory(id).enqueue(new Callback<ResponseCategoryProtocol>() {
            @Override
            public void onResponse(Call<ResponseCategoryProtocol> call, Response<ResponseCategoryProtocol> response) {
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


                Toast.makeText(getApplication(), "SUCCESS", Toast.LENGTH_LONG).show();
                gridLayoutManager = new GridLayoutManager(ProtocolCategoryActivity.this, 2);
                mRecyclerView.setLayoutManager(gridLayoutManager);


                mAdapter = new RecyclerAdapterProtocolCategory(registeredUser.getDocuments(), ProtocolCategoryActivity.this);

                mRecyclerView.setAdapter(mAdapter);


            }

            @Override
            public void onFailure(Call<ResponseCategoryProtocol> call, Throwable t) {
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
