package com.example.d.healthbook.Fragments;

import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.d.healthbook.Activities.MainPageActivity;
import com.example.d.healthbook.Adapters.CustomPagerAdapter;
import com.example.d.healthbook.Appi.App;
import com.example.d.healthbook.GlobalVariables.GlobalVariables;
import com.example.d.healthbook.R;

import java.util.List;


public class ViewPagerFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String VIEW_TYPE_KEY = "view_type";


    // TODO: Rename and change types of parameters
    private String mParam1 = "first";

    private OnFragmentInteractionListener mListener;
    private String TAG = "VIEPAGERFRAGMENT";
    private LinearLayout fragment_LL_viewPager;


    @RequiresApi(api = Build.VERSION_CODES.M)
    public void changeBackground() {
        fragment_LL_viewPager.setBackgroundColor(Color.parseColor("#33000000"));
    }

    public ViewPagerFragment() {
        // Required empty public constructor
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ((MainPageActivity) getActivity()).changeMenuItems(1);
        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        viewPager = (ViewPager) view.findViewById(R.id.pager);
        fragment_LL_viewPager = (LinearLayout) view.findViewById(R.id.fragment_LL_viewPager);
        setViews(mParam1);
        ((MainPageActivity) getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu_black_24x24);


    }


    // TODO: Rename and change types and number of parameters
    public static ViewPagerFragment newInstance(String param1) {
        ViewPagerFragment fragment = new ViewPagerFragment();
        Bundle args = new Bundle();
        args.putString(VIEW_TYPE_KEY, param1);
        fragment.setArguments(args);
        return fragment;
    }


    public CustomPagerAdapter customPagerAdapter = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    TabLayout tabLayout;
    private ViewPager viewPager;
    List<String> sendDataList;

    void setDoctorAndClinicsView() {
//        sendDataList
        tabLayout.addTab(tabLayout.newTab().setText("Врачи"));
        tabLayout.addTab(tabLayout.newTab().setText("Клиники"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        customPagerAdapter = new CustomPagerAdapter(getActivity(),
                getActivity().getSupportFragmentManager(), tabLayout.getTabCount());


        viewPager.setAdapter(customPagerAdapter);


        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == 0) {
                    Log.d(TAG, "CПИСОК ВРАЧЕЙ");
                    App.getInstance().changeToolbarTitle((AppCompatActivity) getActivity(), "Список врачей");
//                    ((MainPageActivity) getActivity()).changeMenuItems(1);
                    changeMenuIconVisibility(1);
                    GlobalVariables.isClinic = false;
                } else {
                    changeMenuIconVisibility(1);
                    GlobalVariables.isClinic = true;
                    App.getInstance().changeToolbarTitle((AppCompatActivity) getActivity(), "Список клиник");

                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    public void changeMenuIconVisibility(int a) {
        ((MainPageActivity) getActivity()).changeMenuItems(a);
    }

    void setViews(String view_type) {
        switch (view_type) {
            case "first": {
                setDoctorAndClinicsView();
                break;
            }
            default: {
                break;
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_pager, container, false);
    }


    // TODO: Rename method, update argument and hook method into UI event


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
