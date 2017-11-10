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
import com.example.d.healthbook.Adapters.CustomPagerAdapter;
import com.example.d.healthbook.Adapters.CustomPagerAdapterKnowledges;
import com.example.d.healthbook.Appi.App;
import com.example.d.healthbook.GlobalVariables.GlobalVariables;
import com.example.d.healthbook.R;

import java.util.List;

/**
 * Created by D on 16.06.2017.
 */

public class ViewPagerKnowledgeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String VIEW_TYPE_KEY = "view_type";


    // TODO: Rename and change types of parameters
    private String mParam1 = "first";

    private OnFragmentInteractionListener mListener;

    public ViewPagerKnowledgeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ((MainPageActivity) getActivity()).changeMenuItems(0);
        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        viewPager = (ViewPager) view.findViewById(R.id.pager);
        setViews(mParam1);
        ((MainPageActivity) getActivity()).getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu_black_24x24);
    }


    // TODO: Rename and change types and number of parameters
    public static ViewPagerKnowledgeFragment newInstance(String param1) {
        ViewPagerKnowledgeFragment fragment = new ViewPagerKnowledgeFragment();
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

    void setDoctorAndClinicsView() {
//        sendDataList
        tabLayout.addTab(tabLayout.newTab().setText("Библиотека протоколов"));
        tabLayout.addTab(tabLayout.newTab().setText("Библиотека лекарств"));
        tabLayout.addTab(tabLayout.newTab().setText("Библиотека болезней"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        CustomPagerAdapterKnowledges adapter = null;

        adapter = new CustomPagerAdapterKnowledges(getActivity(),
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
                    App.getInstance().changeToolbarTitle((AppCompatActivity) getActivity(), "Библиотека протоколов");

                } else if (position == 1) {
                    App.getInstance().changeToolbarTitle((AppCompatActivity) getActivity(), "Библиотека лекарств");

                } else {
                    App.getInstance().changeToolbarTitle((AppCompatActivity) getActivity(), "Библиотека болезней");
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


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
