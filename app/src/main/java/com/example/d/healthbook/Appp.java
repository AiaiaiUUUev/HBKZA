package com.example.d.healthbook;

import android.app.Application;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by D on 26.07.2017.
 */

public class Appp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Myriad Pro Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}