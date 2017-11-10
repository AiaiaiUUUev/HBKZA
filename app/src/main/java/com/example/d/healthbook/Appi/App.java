package com.example.d.healthbook.Appi;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.d.healthbook.R;
import com.example.d.healthbook.View.ActivityView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


public class App extends Application {

    private static App _instance;
    private static BaseAPI api;
    private Retrofit retrofit;

    private Activity _activity;
    private Context _context;
    private Fragment _fragment;

    public int BackPressTreshHold = 1800;


    @Override
    public void onCreate() {
        super.onCreate();


        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Myriad Pro Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();


        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .addInterceptor(logging).build();


//        .baseUrl("http://idocta.kz/")
        retrofit = new Retrofit.Builder()
                .baseUrl("http://healthbook.kz/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
//                .client(httpClient.build())
                .client(client)
                .build();
        api = retrofit.create(BaseAPI.class);
    }

    public static BaseAPI getApi() {
        return api;
    }


    public void clear() {
        _activity = null;
        _context = null;
        _fragment = null;
    }

    public void setCurrentFragment(Fragment fragment) {
        _fragment = fragment;
    }

    public void setCurrentActivity(Activity activity) {
        _activity = activity;
    }

    public Activity get_activity() {
        return _activity;
    }

    public void removeSubFragment(AppCompatActivity _context){
        if(subFragment!=null){
//            _context.getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.popup_exit, R.anim.disappeare_animation).remove(subFragment).commit();

            _context.getSupportFragmentManager().beginTransaction().remove(subFragment).commit();
        }
    }
    public void setFragment(AppCompatActivity _context, String fragmentName, int anim_in, int anim_out,
                            boolean add_to_back_stack, Bundle bundle, Boolean showSearchList) {
        Fragment fragment = Fragment.instantiate(_context, fragmentName);
        fragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = _context.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(anim_in, anim_out, anim_in, anim_out);
        if (showSearchList) {
            fragmentTransaction.replace(R.id.changable_fragment_container, fragment);
        } else {
            fragmentTransaction.replace(R.id.fragment_container, fragment);
        }
        if (add_to_back_stack) fragmentTransaction.addToBackStack(fragmentName);
        fragmentTransaction.commit();
    }

    public void setFragment(AppCompatActivity _context, String fragmentName, int anim_in, int anim_out, boolean add_to_back_stack) {
        Fragment fragment = Fragment.instantiate(_context, fragmentName);
        FragmentTransaction fragmentTransaction = _context.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(anim_in, anim_out, anim_in, anim_out);
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        if (add_to_back_stack) fragmentTransaction.addToBackStack(fragmentName);
        fragmentTransaction.commit();
    }


    public Fragment subFragment;

    public void addFragment(AppCompatActivity _context, String fragmentName, int anim_in, int anim_out, boolean add_to_back_stack) {
        subFragment = Fragment.instantiate(_context, fragmentName);
        FragmentTransaction fragmentTransaction = _context.getSupportFragmentManager().beginTransaction();
     //TODO:CHECK
        //   fragmentTransaction.setCustomAnimations(anim_in, R.anim.disappeare_animation);
        fragmentTransaction.add(R.id.fragment_container, subFragment);
     //   if (add_to_back_stack) fragmentTransaction.addToBackStack(fragmentName);
        fragmentTransaction.commit();
    }

    public void setFragmentBottom(AppCompatActivity _context, String fragmentName, int anim_in, int anim_out, boolean add_to_back_stack) {
        Fragment fragment = Fragment.instantiate(_context, fragmentName);
        FragmentTransaction fragmentTransaction = _context.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(anim_in, anim_out, anim_in, anim_out);
        fragmentTransaction.replace(R.id.changable_fragment_container, fragment);
        if (add_to_back_stack) fragmentTransaction.addToBackStack(fragmentName);
        fragmentTransaction.commit();
    }

    public void setFragmentBottom(AppCompatActivity _context, String fragmentName, int anim_in, int anim_out, boolean add_to_back_stack, Bundle bundle) {
        Fragment fragment = Fragment.instantiate(_context, fragmentName);
        fragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = _context.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(anim_in, anim_out, anim_in, anim_out);
        fragmentTransaction.replace(R.id.changable_fragment_container, fragment);
        if (add_to_back_stack) fragmentTransaction.addToBackStack(fragmentName);
        fragmentTransaction.commit();
    }

    public void setFragment(AppCompatActivity _context, String fragmentName, int anim_in, int anim_out, boolean add_to_back_stack, int FrameViewID) {
        Fragment fragment = Fragment.instantiate(_context, fragmentName);
        FragmentTransaction fragmentTransaction = _context.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(anim_in, anim_out, anim_in, anim_out);
        fragmentTransaction.replace(FrameViewID, fragment);
        if (add_to_back_stack) fragmentTransaction.addToBackStack(fragmentName);
        fragmentTransaction.commit();
    }


    public <T> T getFragment(Context _ctx) {
        return ((T) ((AppCompatActivity) _ctx).getSupportFragmentManager().findFragmentById(R.id.fragment_container));
    }

    public static App getInstance() {
        return (_instance == null) ? _instance = new App() : _instance;
    }

    public void changeToolbarTitle(AppCompatActivity act, String title_page) {
        act.getSupportActionBar().setTitle("");
        ((ActivityView) act).setActivityTitle(title_page);
    }



    public Fragment getCurrentFragment() {
        return _fragment;
    }

    private Context mContext;

    public void setCurrentContext(Context _ctx) {
        mContext = _ctx;

    }

    public Context getCurrentContext() {
        return mContext;
    }



}
