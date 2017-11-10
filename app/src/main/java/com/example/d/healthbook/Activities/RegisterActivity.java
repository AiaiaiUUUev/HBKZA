package com.example.d.healthbook.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.d.healthbook.Appi.App;
import com.example.d.healthbook.Fragments.RegistrationFragment;
import com.example.d.healthbook.R;

/**
 * Created by D on 29.05.2017.
 */

public class RegisterActivity extends AppCompatActivity{
    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        App.getInstance().setFragment(this,RegistrationFragment.class.getName(), android.R.anim.fade_in,
                android.R.anim.fade_out, true);
    }

}
