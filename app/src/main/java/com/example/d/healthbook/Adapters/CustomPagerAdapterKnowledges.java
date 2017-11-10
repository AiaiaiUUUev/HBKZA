package com.example.d.healthbook.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.d.healthbook.Fragments.LibraryDiseasesFragment;
import com.example.d.healthbook.Fragments.LibraryDrugsFragment;
import com.example.d.healthbook.Fragments.LibraryProtocolFragment;

/**
 * Created by D on 16.06.2017.
 */

public class CustomPagerAdapterKnowledges extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    private Context mContext;
    private String titleClinic, cityName, speciality;
    private Boolean checkGetData = false;

    public CustomPagerAdapterKnowledges(Context context, FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.mContext = context;
    }


    LibraryProtocolFragment tab1;
    LibraryDrugsFragment tab2;
    LibraryDiseasesFragment tab3;

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                tab1 = new LibraryProtocolFragment();

                return tab1;
            case 1:
                tab2 = new LibraryDrugsFragment();

                return tab2;
            case 2:
                tab3 = new LibraryDiseasesFragment();

                return tab3;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }


}
