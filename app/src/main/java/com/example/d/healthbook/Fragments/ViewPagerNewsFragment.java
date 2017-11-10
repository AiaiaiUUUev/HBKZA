package com.example.d.healthbook.Fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.d.healthbook.Activities.MainPageActivity;
import com.example.d.healthbook.Adapters.CustomPagerAdapterNews;
import com.example.d.healthbook.Appi.App;
import com.example.d.healthbook.GlobalVariables.GlobalVariables;
import com.example.d.healthbook.R;

import java.util.List;

/**
 * Created by D on 19.06.2017.
 */

public class ViewPagerNewsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String VIEW_TYPE_KEY = "view_type";


    // TODO: Rename and change types of parameters
    private String mParam1 = "first";

    private ViewPagerKnowledgeFragment.OnFragmentInteractionListener mListener;

    public ViewPagerNewsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ((MainPageActivity) getActivity()).changeMenuItems(2);

        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        viewPager = (ViewPager) view.findViewById(R.id.pager);
        setViews(mParam1);
        ((MainPageActivity) getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu_black_24x24);
    }


    // TODO: Rename and change types and number of parameters
    public static ViewPagerNewsFragment newInstance(String param1) {
        ViewPagerNewsFragment fragment = new ViewPagerNewsFragment();
        Bundle args = new Bundle();
        args.putString(VIEW_TYPE_KEY, param1);
        fragment.setArguments(args);
        return fragment;
    }

    private String titleClinic;
    private String cityName;
    private String speciality;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            titleClinic = getArguments().getString(GlobalVariables.titleClinic_key);
            cityName = getArguments().getString(GlobalVariables.cityName_key);
            speciality = getArguments().getString(GlobalVariables.speciality_key);
        }
    }

    TabLayout tabLayout;
    ViewPager viewPager;
    List<String> sendDataList;

    void seeNewsAndChannel() {
//        sendDataList
        tabLayout.addTab(tabLayout.newTab().setText("Новости"));
        tabLayout.addTab(tabLayout.newTab().setText("Подписки"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        CustomPagerAdapterNews adapter = null;

        adapter = new CustomPagerAdapterNews(getActivity(),
                getActivity().getSupportFragmentManager(), tabLayout.getTabCount());


        viewPager.setAdapter(adapter);
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
                    App.getInstance().changeToolbarTitle((AppCompatActivity) getActivity(), "Новости");
                    ((MainPageActivity) getActivity()).changeMenuItems(2);
                } else {
                    App.getInstance().changeToolbarTitle((AppCompatActivity) getActivity(), "Подписки");
                    ((MainPageActivity) getActivity()).changeMenuItems(0);
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

    void setViews(String view_type) {
        switch (view_type) {
            case "first": {
                seeNewsAndChannel();
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
