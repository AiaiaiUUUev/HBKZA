package com.example.d.healthbook.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.d.healthbook.FragmentsTab.TabFragment2;
import com.example.d.healthbook.FragmentsTab.TabFragmentMentionsClinic;
import com.example.d.healthbook.FragmentsTab.TabFragment4;
import com.example.d.healthbook.FragmentsTab.TabFragmentInfoClinic;
import com.example.d.healthbook.Models.ResponseClinicInfo2;
import com.example.d.healthbook.View.ClinicInterface;
import com.example.d.healthbook.View.ViewPagerClinicInterface;

/**
 * Created by D on 09.06.2017.
 */

public class PagerAdapterClinicInfo extends FragmentStatePagerAdapter implements ViewPagerClinicInterface {
    int mNumOfTabs;
    private Context mContext;

    public PagerAdapterClinicInfo(Context context, FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.mContext = context;
    }


    TabFragmentInfoClinic tab1;
    TabFragment2 tab2;
    TabFragmentMentionsClinic tab3;

    TabFragment4 tab4;

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                tab1 = new TabFragmentInfoClinic();
                if( ((ClinicInterface)mContext).getData()!=null){
                    tab1.upDateData(((ClinicInterface)mContext).getData());
                }
                return tab1;
            case 1:
                tab2 = new TabFragment2();
//                if( ((DoctorInterface)mContext).getData()!=null){
//                    tab2.upDateData(((DoctorInterface)mContext).getData());
//                }
                return tab2;

            case 2:
//                tab3 = tab3 == null ? new TabFragmentSchedule() : tab3;
                tab3 = new TabFragmentMentionsClinic();
//                if( ((DoctorInterface)mContext).getData()!=null){
//                    tab3.upDateData(((DoctorInterface)mContext).getData());
//                }
                return tab3;
            case 3:
                tab4 = new TabFragment4();
//                if( ((DoctorInterface)mContext).getData()!=null){
//                    tab4.upDateData(((DoctorInterface)mContext).getData());
//                }
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
    public void onResponseSuccess(ResponseClinicInfo2 responseData) {
        if (tab1 != null) {
            tab1.upDateData(responseData);
        }
//        if (tab2 != null) {
//            tab2.upDateData(responseData);
//        }
//        if (tab3 != null) {
//            tab3.upDateData(responseData);
//        }
//        if (tab4 != null) {
//            tab4.upDateData(responseData);
//        }
    }

}
