package com.example.d.healthbook.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.d.healthbook.FragmentsTab.TabFragmentInfoDoc;
import com.example.d.healthbook.FragmentsTab.TabFragmentReview;
import com.example.d.healthbook.FragmentsTab.TabFragmentSchedule;
import com.example.d.healthbook.FragmentsTab.TabFragmentStatus;
import com.example.d.healthbook.Models.ResponseDoctorInfo;
import com.example.d.healthbook.View.DoctorInterface;
import com.example.d.healthbook.View.ViewPagerInterface;

/**
 * Created by D on 02.06.2017.
 */

public class PagerAdapterDoctorInfo extends FragmentStatePagerAdapter implements ViewPagerInterface {
    int mNumOfTabs;
    private Context mContext;


    public PagerAdapterDoctorInfo(Context context, FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.mContext = context;
    }

    TabFragmentInfoDoc tab1;
    TabFragmentReview tab3;
    TabFragmentSchedule tab2;
    TabFragmentStatus tab4;

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                tab1 = new TabFragmentInfoDoc();
                if (((DoctorInterface) mContext).getData() != null) {
                    tab1.upDateData(((DoctorInterface) mContext).getData());
                }
                return tab1;
            case 1:
                tab2 = new TabFragmentSchedule();
                if (((DoctorInterface) mContext).getData() != null) {
                    tab2.upDateData(((DoctorInterface) mContext).getData());
                }
                return tab2;

            case 2:
//                tab3 = tab3 == null ? new TabFragmentSchedule() : tab3;
                tab3 = new TabFragmentReview();
                if (((DoctorInterface) mContext).getData() != null) {
                    tab3.upDateData(((DoctorInterface) mContext).getData());
                }
                return tab3;
            case 3:
                tab4 = new TabFragmentStatus();
                if (((DoctorInterface) mContext).getData() != null) {
                    tab4.upDateData(((DoctorInterface) mContext).getData());
                }
                return tab4;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

    @Override
    public void onResponseSuccess(ResponseDoctorInfo responseData) {
        if (tab1 != null) {
            tab1.upDateData(responseData);
        }
        if (tab2 != null) {
            tab2.upDateData(responseData);
        }
        if (tab3 != null) {
            tab3.upDateData(responseData);
        }
        if (tab4 != null) {
            tab4.upDateData(responseData);
        }
    }
}