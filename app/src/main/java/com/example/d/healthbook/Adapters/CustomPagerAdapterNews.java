package com.example.d.healthbook.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.d.healthbook.Fragments.ChannelFragment;
import com.example.d.healthbook.Fragments.LibraryDiseasesFragment;
import com.example.d.healthbook.Fragments.LibraryDrugsFragment;
import com.example.d.healthbook.Fragments.LibraryProtocolFragment;
import com.example.d.healthbook.Fragments.NewsListFragment;

/**
 * Created by D on 19.06.2017.
 */


public class CustomPagerAdapterNews extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    private Context mContext;
    private String titleClinic, cityName, speciality;
    private Boolean checkGetData = false;

    public CustomPagerAdapterNews(Context context, FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.mContext = context;
    }


    NewsListFragment tab1;
    ChannelFragment tab2;


    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                tab1 = new NewsListFragment();

                return tab1;
            case 1:
                tab2 = new ChannelFragment();

                return tab2;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }


}

