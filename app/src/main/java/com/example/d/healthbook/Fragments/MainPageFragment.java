package com.example.d.healthbook.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.d.healthbook.Activities.MainPageActivity;
import com.example.d.healthbook.Appi.App;
import com.example.d.healthbook.GlobalVariables.GlobalVariables;
import com.example.d.healthbook.R;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainPageFragment extends Fragment {


    private OnFragmentInteractionListener mListener;
    private boolean checkCurrentFragment = false;


    public MainPageFragment() {
        // Required empty public constructor
    }

    public static MainPageFragment newInstance(String param1, String param2) {
        MainPageFragment fragment = new MainPageFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }


    @BindView(R.id.bottomBar)
    BottomBar bottomBar;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_page, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        ((MainPageActivity) getActivity()).changeImageVisibility(0);

        App.getInstance().setFragment((MainPageActivity) getActivity(), ViewPagerFragment.class.getName(),
                android.R.anim.fade_in, android.R.anim.fade_out, true, R.id.changable_fragment_container);


        App.getInstance().setCurrentFragment(this);


        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.tab_clinic_doc) {
                    if (App.getInstance().getCurrentFragment() instanceof MainPageFragment && !checkCurrentFragment) {
                        checkCurrentFragment = true;
                    } else {
                        App.getInstance().setFragmentBottom((MainPageActivity) getActivity(), ViewPagerFragment.class.getName(),
                                android.R.anim.fade_in, android.R.anim.fade_out, false);
                        GlobalVariables.checkMenuBtn = false;

//                        ((MainPageActivity) getActivity()).changeMenuItems(1);
                    }
                }
                if (tabId == R.id.tab_feed) {
                    App.getInstance().setFragmentBottom((MainPageActivity) getActivity(), FeedListFragment.class.getName(),
                            android.R.anim.fade_in, android.R.anim.fade_out, false);
                    GlobalVariables.checkMenuBtn = true;

                }
                if (tabId == R.id.tab_news) {
                    App.getInstance().setFragmentBottom((MainPageActivity) getActivity(), ViewPagerNewsFragment.class.getName(),
                            android.R.anim.fade_in, android.R.anim.fade_out, false);

                }
                if (tabId == R.id.tab_knowledge) {
                    App.getInstance().setFragmentBottom((MainPageActivity) getActivity(), ViewPagerKnowledgeFragment.class.getName(),
                            android.R.anim.fade_in, android.R.anim.fade_out, false);

                }
            }
        });
        //   ((MainPageActivity) App.getInstance().get_activity()).setActivityToolbarIcon(R.menu.menu_profile_layout);
        // ((MainPageActivity) getActivity()).getMenuInflater().inflate(R.menu.menu_profile_layout,((MainPageActivity) getActivity()).getMenu());
        ((MainPageActivity) getActivity()).changeMenuItems(1);
        //ViewCompat.setNestedScrollingEnabled(view.findViewById(R.id.changable_fragment_container) ,false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
