package com.example.d.healthbook.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.d.healthbook.Activities.MainPageActivity;
import com.example.d.healthbook.Appi.App;
import com.example.d.healthbook.Controller.MainController;
import com.example.d.healthbook.GlobalVariables.GlobalVariables;
import com.example.d.healthbook.Models.ResponseDoctorList;
import com.example.d.healthbook.Models.ResponseGetUserData;
import com.example.d.healthbook.R;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UserProfileFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    Toolbar toolbar;


    @BindView(R.id.text_field_first_name)
    TextView text_field_first_name;
    @BindView(R.id.text_field_last_name)
    TextView text_field_last_name;
    @BindView(R.id.text_field_date_of_birth)
    TextView text_field_date_of_birth;
    @BindView(R.id.text_field_gender)
    TextView text_field_gender;
    @BindView(R.id.text_field_blood_group)
    TextView text_field_blood_group;
    @BindView(R.id.text_field_phone)
    TextView text_field_phone;
    @BindView(R.id.text_field_mail)
    TextView text_field_mail;
    @BindView(R.id.text_field_allergens)
    TextView text_field_allergens;
    @BindView(R.id.text_field_chronical_diseases)
    TextView text_field_chronical_diseases;
    @BindView(R.id.text_field_inheritance)
    TextView text_field_inheritance;
    @BindView(R.id.profile_image)
    CircleImageView profile_image;
//    @BindView(R.id.bottomBar)
//    BottomBar bottomBar;


    private ResponseGetUserData registeredUser;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //App.getInstance().setCurrentFragment(this);

        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.profile_layout, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //  Activity context = (Activity) Application.getInstance().getCurrentActivity();
        //  getActivity().getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((MainPageActivity) getActivity()).changeMenuItems(0);
        ((MainPageActivity)getActivity()).changeImageVisibility(0);
        // toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        // ToolbarColorizeHelper.colorizeToolbar(toolbar, ContextCompat.getColor(getContext(), R.color.transparent_background), ContextCompat.getColor(getContext(), R.color.colorWhite));
        App.getInstance().changeToolbarTitle((AppCompatActivity) getActivity(), "Аккаунт");
        App.getInstance().setCurrentFragment(this);

//        seeUserInfo();
        if (GlobalVariables.responseGetUserData != null) {
            seeUserInfo();
        }



    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_profile_layout, menu);

        for (int i = 0; i < menu.size(); i++) {
            if (!(menu.getItem(i) instanceof MenuItem)) continue;

            final PorterDuffColorFilter srcColorFilter = new PorterDuffColorFilter(ContextCompat.getColor(getContext(), R.color.colorWhite), PorterDuff.Mode.SRC_IN);
            Drawable icon = menu.getItem(i).getIcon();
            icon.setColorFilter(srcColorFilter);
            menu.getItem(i).setIcon(icon);
        }
    }

    public void seeUserInfo() {


        text_field_first_name.setText(GlobalVariables.responseGetUserData.getName() + " " + GlobalVariables.responseGetUserData.getMiddleName());
        text_field_last_name.setText(GlobalVariables.responseGetUserData.getSurname());
        text_field_gender.setText(getFormattedTextBlock("Пол: " + GlobalVariables.responseGetUserData.getGender(), Color.rgb(0, 0, 0), 0, 4));
        text_field_date_of_birth.setText(getFormattedTextBlock("Дата рождения: " + GlobalVariables.responseGetUserData.getBirthday(), Color.rgb(0, 0, 0), 0, 14));
        text_field_blood_group.setText(getFormattedTextBlock("Группа крови: " + GlobalVariables.responseGetUserData.getBloodGroup(), Color.rgb(0, 0, 0), 0, 13));
        text_field_phone.setText(getFormattedTextBlock("Телефон: " + GlobalVariables.responseGetUserData.getPhone(), Color.rgb(0, 0, 0), 0, 8));
        text_field_mail.setText(getFormattedTextBlock("E-mail: " + GlobalVariables.responseGetUserData.getEmail(), Color.rgb(0, 0, 0), 0, 7));
        text_field_allergens.setText(getFormattedTextBlock("Аллергия\n" + GlobalVariables.responseGetUserData.getAllergy(), Color.rgb(0, 0, 0), 0, 8));
        text_field_chronical_diseases.setText(getFormattedTextBlock("Хронические заболевания:\n" + GlobalVariables.responseGetUserData.getChronicDiseases(), Color.rgb(0, 0, 0), 0, 24));
        text_field_inheritance.setText(getFormattedTextBlock("Наследственность:\n" + GlobalVariables.responseGetUserData.getHeredity(), Color.rgb(0, 0, 0), 0, 17));
        MainController.setImageToViewById(getActivity() ,GlobalVariables.responseGetUserData.getPhoto() ,profile_image );


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_edit_profile:
                //  App.getInstance().setFragment(SetUserProfileFragment.class.getName(), android.R.anim.slide_in_left, android.R.anim.slide_out_right, false);
                Toast.makeText(this.getActivity(), "Test", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }


    private SpannableStringBuilder getFormattedTextBlock(String content, int color, int setColorStartIndex, int setColorEndIndex) {
        final SpannableStringBuilder sb = new SpannableStringBuilder(content);
        final ForegroundColorSpan fcs = new ForegroundColorSpan(color);
        sb.setSpan(fcs, setColorStartIndex, setColorEndIndex, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        return sb;
    }


}
