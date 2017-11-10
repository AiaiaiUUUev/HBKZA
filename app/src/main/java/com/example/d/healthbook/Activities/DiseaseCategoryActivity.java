package com.example.d.healthbook.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.d.healthbook.Appi.App;
import com.example.d.healthbook.Fragments.DiseaseCategoryFragment;
import com.example.d.healthbook.R;
import com.example.d.healthbook.UI.MyToolbar;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class DiseaseCategoryActivity extends AppCompatActivity {


    private MyToolbar toolbar;
    private String id;
    public static boolean backPressed;


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_category);

        toolbar = (MyToolbar) findViewById(R.id.main_page_toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        id = getIntent().getStringExtra("id");
        setFragment();


    }

    public void setFragment() {
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        App.getInstance().setFragment(DiseaseCategoryActivity.this, DiseaseCategoryFragment.class.getName(),
                android.R.anim.fade_in, android.R.anim.fade_out, false, bundle, false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (backPressed) {
                    setFragment();
                    backPressed = false;
                } else {
                    onBackPressed();
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}