package com.example.d.healthbook.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.example.d.healthbook.FragmentsTab.TabUserEditProfile;
import com.example.d.healthbook.FragmentsTab.TabUserInfoMyFamily;
import com.example.d.healthbook.FragmentsTab.TabUserInfoMydata;
import com.example.d.healthbook.FragmentsTab.TabUserInfoProgress;
import com.example.d.healthbook.FragmentsTab.TabUserInfoSubscription;
import com.example.d.healthbook.Models.ResponseAllSubscriptionsToDoctor;
import com.example.d.healthbook.Models.ResponseGetUserData;
import com.example.d.healthbook.Models.ResponseMyFamilyMembers;
import com.example.d.healthbook.Models.ResponseProgressUser;
import com.example.d.healthbook.View.PagerUserInfoInterface;
import com.example.d.healthbook.View.UserInterface;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static com.example.d.healthbook.Activities.UserActivityInfo.clickEditUserProfile;

/**
 * Created by D on 01.07.2017.
 */

public class PagerAdapterUserInfo extends FragmentStatePagerAdapter implements PagerUserInfoInterface {
    int mNumOfTabs;
    private Context mContext;
    private ConcurrentHashMap<Integer, Fragment> registeredFragments;


    public PagerAdapterUserInfo(Context context, FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.mContext = context;
    }

    TabUserInfoMydata tab1;
    TabUserEditProfile tab5;

    TabUserInfoMyFamily tab2;
    TabUserInfoSubscription tab3;
    TabUserInfoProgress tab4;

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                if (!clickEditUserProfile) {
                    tab1 = new TabUserInfoMydata();
                    if (((UserInterface) mContext).getData() != null) {
                        tab1.upDateData(((UserInterface) mContext).getData());
                    }
                    return tab1;
                } else {
                    tab5 = new TabUserEditProfile();
                    return tab5;
                }

            case 1:
                tab2 = new TabUserInfoMyFamily();
                if (((UserInterface) mContext).getDataFamily() != null) {
                    tab2.upDateData(((UserInterface) mContext).getDataFamily());
                }
                return tab2;

            case 2:
                tab3 = new TabUserInfoSubscription();
                if (((UserInterface) mContext).getDataSub() != null) {
                    tab3.upDateData(((UserInterface) mContext).getDataSub());
                }
                return tab3;
            case 3:
                tab4 = new TabUserInfoProgress();
                if (((UserInterface) mContext).getDataProgress() != null) {
                    tab4.upDateData(((UserInterface) mContext).getDataProgress());
                }
                return tab4;
            default:
                return null;
        }
    }



    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

    @Override
    public void onResponseSuccess(ResponseGetUserData responseData) {
        if (tab1 != null) {
            tab1.upDateData(responseData);
        }
    }

    public void OnResponseSuccessAllSubscription(List<ResponseAllSubscriptionsToDoctor> responseData) {
        if (tab3 != null) {
            tab3.upDateData(responseData);
        }
    }

    public void OnResponseSuccessProgress(ResponseProgressUser responseData) {
        if (tab4 != null) {
            tab4.upDateData(responseData);
        }
    }

    public void OnResponseSuccesMyfamily(ResponseMyFamilyMembers responseData) {
        if (tab2 != null) {
            tab2.upDateData(responseData);
        }
    }

}