package com.example.d.healthbook.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.d.healthbook.Adapters.RecyclerAdapterDrugsCategory;
import com.example.d.healthbook.Appi.App;
import com.example.d.healthbook.Models.ResponseDrugsCategory;
import com.example.d.healthbook.R;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by D on 19.06.2017.
 */

public class FragmentShowInfoCategory extends Fragment {
    private boolean viewCreated = false;
    private String body_ru;
    private String title;
    private TextView title_show_fragment;
    private TextView info_show_fragment;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        if (getArguments() != null) {
            body_ru = getArguments().getString("body_ru");
            title = getArguments().getString("title");
        }



    }

    private void showInfoCategory(String body_ru, String title) {
        if (viewCreated) {
            title_show_fragment.setText(title);
            info_show_fragment.setText(body_ru);
        }

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //App.getInstance().setCurrentFragment(this);

        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_show_info_category, container, false);


    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        App.getInstance().changeToolbarTitle((AppCompatActivity) getActivity(), "Список врачей");

        title_show_fragment = (TextView) view.findViewById(R.id.title_show_fragment);
        info_show_fragment = (TextView) view.findViewById(R.id.info_show_fragment);
        viewCreated = true;
        showInfoCategory(body_ru, title);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


}