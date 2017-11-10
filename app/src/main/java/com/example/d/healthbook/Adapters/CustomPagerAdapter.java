package com.example.d.healthbook.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.example.d.healthbook.Fragments.SearchDoctorsFragment;
import com.example.d.healthbook.Fragments.See_Clinic_List_Fragment;

import java.util.HashMap;

/**
 * Created by D on 07.06.2017.
 */

public class CustomPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    private Context mContext;

    private  Fragment mCurrentFragment;

    public CustomPagerAdapter(Context context, FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.mContext = context;
    }



    SearchDoctorsFragment tab1;
    See_Clinic_List_Fragment tab2;


    public  Fragment getCurrentFragment() {
        return mCurrentFragment;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        if (getCurrentFragment() != object) {
            mCurrentFragment = ((Fragment) object);
        }
        super.setPrimaryItem(container, position, object);
    }




    private static String makeFragmentName(int viewId, int index) {
        return "android:switcher:" + viewId + ":" + index;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                tab1 = new SearchDoctorsFragment();
                return tab1;
            case 1:

                tab2 = new See_Clinic_List_Fragment();
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
