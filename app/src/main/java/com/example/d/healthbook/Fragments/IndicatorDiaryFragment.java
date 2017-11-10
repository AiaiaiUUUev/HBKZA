package com.example.d.healthbook.Fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.d.healthbook.Activities.MainPageActivity;
import com.example.d.healthbook.Appi.App;
import com.example.d.healthbook.Nurlans.CompositeBlock;
import com.example.d.healthbook.Nurlans.CoverBlock;
import com.example.d.healthbook.Nurlans.ICBlock;
import com.example.d.healthbook.Nurlans.ICTBlock;
import com.example.d.healthbook.Nurlans.ImageBlock;
import com.example.d.healthbook.Nurlans.TextBlock;
import com.example.d.healthbook.R;
import com.example.d.healthbook.UI.BlockLayoutFabrique;
import com.example.d.healthbook.UI.Size;
import com.liulishuo.magicprogresswidget.MagicProgressCircle;


public class IndicatorDiaryFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        App.getInstance().setCurrentFragment(this);
        return inflater.inflate(R.layout.main_page_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {


        MainPageActivity activity = (MainPageActivity) getActivity();


        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        FrameLayout frame_lt = (FrameLayout) getActivity().findViewById(R.id.fragment_container);
//        RelativeLayout.LayoutParams lt_params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        lt_params.addRule(RelativeLayout.BELOW, R.id.toolbar);
//        frame_lt.setLayoutParams(lt_params);
//        ToolbarColorizeHelper.colorizeToolbar(toolbar, ContextCompat.getColor(getContext(), R.color.colorWhite), ContextCompat.getColor(getContext(), R.color.colorAqua));

        TextView toolbar_title = (TextView) activity.findViewById(R.id.toolbar_title);
        toolbar_title.setTextColor(ContextCompat.getColor(getContext(), R.color.bpBlue));

        toolbar_title.setVisibility(View.VISIBLE);
        toolbar_title.setText(R.string.app_name);

        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);

        float percentage = (float) 0.82;

        MagicProgressCircle activity_progress_bar_indicator = (MagicProgressCircle) view.findViewById(R.id.activity_progress_bar_text);
        activity_progress_bar_indicator.setSmoothPercent(percentage);
        TextView activity_progress_bar_text_indicator = (TextView) view.findViewById(R.id.activity_progress_bar_text_indicator);
        activity_progress_bar_text_indicator.setText(String.valueOf((int) (percentage * 100)) + "%");

        //ProgressBar progressBar = (ProgressBar) view.findViewById(R.id.circle_progress_bar);
        //progressBar.setProgress(75);

        ViewGroup viewGroup = (ViewGroup) view.findViewById(R.id.metrics);

        CompositeBlock block0 = (CompositeBlock) BlockLayoutFabrique.getBlock(CompositeBlock.class);
        block0.addGroup("g0", ViewGroup.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT, LinearLayout.VERTICAL);
        block0.addTextField("g0", "Двигаться: 30 мин. за день", R.color.colorBlack, 14, Typeface.DEFAULT_BOLD);
        block0.addGroup("g0", "g1", RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT, LinearLayout.HORIZONTAL);
        block0.addMultilineCheckbox("g1", "П");
        block0.addMultilineCheckbox("g1", "В");
        block0.addMultilineCheckbox("g1", "С");
        block0.addMultilineCheckbox("g1", "Ч");
        block0.addMultilineCheckbox("g1", "П");
        block0.addMultilineCheckbox("g1", "С");
        block0.addMultilineCheckbox("g1", "В");
        block0.getGroup("g1").setPadding(0, 10, 0, 0);
        block0.addGroup("g2", RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        block0.addCircularProgressBar("g2", "prg", 100, 100, 6, R.color.colorDarkGray);
        block0.setAlignment("g2", RelativeLayout.ALIGN_PARENT_RIGHT);
        block0.getGroup("g2").setPadding(0, 10, 10, 0);
        block0.setMargin(10, 10, 10, 10);
        block0.setPadding(20, 20, 20, 20);
        block0.adjustToView(viewGroup, new Size(1, 0));

        ImageBlock block1 = (ImageBlock) BlockLayoutFabrique.getBlock(ImageBlock.class);
        block1.setLabelText("Вода");
        block1.setImageResourse(R.drawable.ic_water);
        block1.setTextValue("9");
        block1.setTextValueColor(R.color.colorWhite);
        block1.setMargin(10, 10, 10, 10);
        TextBlock block2 = (TextBlock) BlockLayoutFabrique.getBlock(TextBlock.class);
        block2.setLabelText("Вес");
        block2.setValueText("55");
        block2.setValueColor(R.color.coral_blue);
        block2.setMargin(10, 10, 10, 10);
        TextBlock block3 = (TextBlock) BlockLayoutFabrique.getBlock(TextBlock.class);
        block3.setLabelText("Сон");
        block3.setValueText("55");
        block3.setValueColor(R.color.md_red_400);
        block3.setMargin(10, 10, 10, 10);
        TextBlock block4 = (TextBlock) BlockLayoutFabrique.getBlock(TextBlock.class);
        block4.setLabelText("Давление");
        block4.setValueText("120/80");
        block4.setMargin(10, 10, 10, 10);
        TextBlock block5 = (TextBlock) BlockLayoutFabrique.getBlock(TextBlock.class);
        block5.setLabelText("Сахар");
        block5.setValueText("4,5");
        block5.setMargin(10, 10, 10, 10);
        CoverBlock block6 = (CoverBlock) BlockLayoutFabrique.getBlock(CoverBlock.class);
        block6.setHeadingText("+");
        block6.setDescriptionText("Управление данными");
        block6.setMargin(10, 10, 10, 10);
        ICTBlock block7 = (ICTBlock) BlockLayoutFabrique.getBlock(ICTBlock.class);
        block7.setImageResourse(R.drawable.ic_menu_camera);
        block7.setContentText("Креон");
        block7.setTime("18:00");
        block7.setMargin(10, 10, 10, 5);
        ICBlock block8 = (ICBlock) BlockLayoutFabrique.getBlock(ICBlock.class);
        block8.setImageResourse(R.drawable.ic_menu_camera);
        block8.setContentText("Добавить напоминание о графике приема лекарств");
        block8.setMargin(10, 5, 10, 10);

        block1.adjustToView(viewGroup, new Size(0.33, 1));
        block2.adjustToView(viewGroup, new Size(0.33, 1));
        block3.adjustToView(viewGroup, new Size(0.33, 1));
        block4.adjustToView(viewGroup, new Size(0.33, 1));
        block5.adjustToView(viewGroup, new Size(0.33, 1));
        block6.adjustToView(viewGroup, new Size(0.33, 1));
        block7.adjustToView(viewGroup, new Size(1, 0));
        block8.adjustToView(viewGroup, new Size(1, 0));

        ViewCompat.setTranslationZ(view.findViewById(R.id.scroll_view), -1000);
    }
}