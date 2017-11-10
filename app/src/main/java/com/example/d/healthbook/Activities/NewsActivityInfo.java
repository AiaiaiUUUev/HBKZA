package com.example.d.healthbook.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.d.healthbook.R;
import com.example.d.healthbook.UI.MyToolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class NewsActivityInfo extends AppCompatActivity {
    @BindView(R.id.imageNewsInfo)
    ImageView imageNewsInfo;
    @BindView(R.id.titleNewsInfo)
    TextView titleNewsInfo;
    @BindView(R.id.categoryName)
    TextView categoryName;
    @BindView(R.id.bodyTextNews)
    TextView bodyTextNews;
    private MyToolbar toolbar;


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_info);
        ButterKnife.bind(this);

        toolbar = (MyToolbar) findViewById(R.id.main_page_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        String image = getIntent().getStringExtra("image");
        String title = getIntent().getStringExtra("titleNews");
        String categoryNameIntent = getIntent().getStringExtra("categoryName");
        String body = getIntent().getStringExtra("body");

        Glide.with(this).load(image).centerCrop().into(imageNewsInfo);
        titleNewsInfo.setText(title);
        categoryName.setText(categoryNameIntent);
        bodyTextNews.setText(body);


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
