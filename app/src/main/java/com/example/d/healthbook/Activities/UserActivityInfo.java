package com.example.d.healthbook.Activities;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.content.res.AppCompatResources;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.d.healthbook.Adapters.PagerAdapterUserInfo;
import com.example.d.healthbook.Appi.App;
import com.example.d.healthbook.CalendarBekarysa.CalendarActivity;
import com.example.d.healthbook.Controller.MainController;
import com.example.d.healthbook.GlobalVariables.GlobalVariables;
import com.example.d.healthbook.Models.ResponseAllSubscriptionsToDoctor;
import com.example.d.healthbook.Models.ResponseEditUserProfile;
import com.example.d.healthbook.Models.ResponseGetUserData;
import com.example.d.healthbook.Models.ResponseMyFamilyMembers;
import com.example.d.healthbook.Models.ResponseProgressUser;
import com.example.d.healthbook.R;
import com.example.d.healthbook.UI.MyToolbar;
import com.example.d.healthbook.View.UserInterface;

import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static com.example.d.healthbook.FragmentsTab.TabUserEditProfile.clickBtnSaveProfileEdit;
import static com.example.d.healthbook.GlobalVariables.GlobalVariables.responseGetUserData;

//import com.example.d.healthbook.Fragments.DialogFragmentEditUserProfile;

public class UserActivityInfo extends AppCompatActivity implements UserInterface {

    private static ResponseMyFamilyMembers registeredUserMyFamily;
    private PagerAdapterUserInfo pagerAdapterUserInfo = null;
    private Intent intent;
    private MyToolbar toolbar;
    private ResponseGetUserData registeredUser;
    @BindView(R.id.profile_imageINFO)
    CircleImageView profile_imageINFO;
    @BindView(R.id.name_surnameTVINFO)
    TextView name_surnameTVINFO;
    @BindView(R.id.edit_TV_UserActivity)
    TextView edit_TV_UserActivity;
    private Fragment currentFragment;


    @BindView((R.id.pager))
    ViewPager viewPager;

    @BindView((R.id.cancelBtnEdit))
    LinearLayout cancelBtnEdit;
    @BindView((R.id.cancelBtnEditIV))
    ImageView cancelBtnEditIV;

    @BindView((R.id.main_appbar))
    AppBarLayout main_appbar;

    @BindView(R.id.toolbar_title)
    TextView toolbar_title;

    //    PagerAdapterUserInfo pagerAdapterUserInfo = null;
    private NavigationView navigation;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mToggle;
    private List<ResponseAllSubscriptionsToDoctor> registeredUserSubscriptions;
    private ResponseProgressUser registeredUserProgress;
    //    private DialogFragmentEditUserProfile dialogFragmentFilterFeed;
    private int MY_REQUEST_CODE = 1;
    public static boolean clickEditUserProfile = false;
    private ResponseEditUserProfile registeredUserEditUser;
    private boolean backBtnClicked;
    private TextView first_name, last_name;


    void _setOn(final int color_value) {
        runOnUiThread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void run() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    toolbar_title.setTextColor(ContextCompat.getColor(UserActivityInfo.this, color_value));
                    edit_TV_UserActivity.setTextColor(ContextCompat.getColor(UserActivityInfo.this, color_value));

                    ColorStateList csl = AppCompatResources.getColorStateList(UserActivityInfo.this, color_value);
                    Drawable drawable = DrawableCompat.wrap(cancelBtnEditIV.getBackground());
                    DrawableCompat.setTintList(drawable, csl);
                    cancelBtnEditIV.setImageDrawable(drawable);
                } else {
                    toolbar_title.setTextColor(getResources().getColor(color_value));
                    edit_TV_UserActivity.setTextColor(getResources().getColor(color_value));

                    ColorStateList csl = AppCompatResources.getColorStateList(UserActivityInfo.this, color_value);
                    Drawable drawable = DrawableCompat.wrap(cancelBtnEditIV.getBackground());
                    DrawableCompat.setTintList(drawable, csl);
                    cancelBtnEditIV.setImageDrawable(drawable);


                }
            }
        });

    }

    public float convertDpToPixel(float dp) {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float px = dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        ButterKnife.bind(this);


        currentFragment = UserActivityInfo.this.getSupportFragmentManager().findFragmentById(R.id.fragment_container);


        toolbar = (MyToolbar) findViewById(R.id.main_page_toolbar);

        navigation = (NavigationView) findViewById(R.id.navigationView);
        View header = navigation.getHeaderView(0);
        first_name = (TextView) header.findViewById(R.id.first_name);
        last_name = (TextView) header.findViewById(R.id.last_name);


        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_tb);
        collapsingToolbarLayout.setTitleEnabled(false);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mToggle.setDrawerIndicatorEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu_black_24x24);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar_title.setText("Аккаунт");

        final int main_appbar_max_height = (int) convertDpToPixel(100);
        main_appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0 || Math.abs(verticalOffset) <= main_appbar_max_height) {
                    _setOn(R.color.white);
                } else {
                    _setOn(R.color.bpBlue);
                }

            }
        });


        seeUserInfo();
        seeUserFamilyMembers();
        seeSubscriptions();
        seeUserProgress();

        navigationClickItemListener();
        cancelANDeditBtnClickListener();

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().

                setText("Мои данные"));
        tabLayout.addTab(tabLayout.newTab().

                setText("Моя семья"));
        tabLayout.addTab(tabLayout.newTab().

                setText("Подписки"));
        tabLayout.addTab(tabLayout.newTab().

                setText("Прогресc"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        navigation.getMenu().getItem(5).setChecked(true);

        pagerAdapterUserInfo = new PagerAdapterUserInfo
                (this, getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapterUserInfo);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()

        {
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
    }


    public void navigationClickItemListener() {
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setCheckable(true);
//                item.setChecked(true);
                navigation.setCheckedItem(item.getItemId());

                int id = item.getItemId();
                switch (id) {
                    case R.id.nav_indicator_diary:
                        intent = new Intent(UserActivityInfo.this, MainPageActivity.class);
                        intent.putExtra("fragment", "IndicatorDiaryFragment");
                        startActivity(intent);
                        finish();
                        drawerLayout.closeDrawer(Gravity.LEFT);

                        break;
                    case R.id.nav_search_doctors:
                        intent = new Intent(UserActivityInfo.this, MainPageActivity.class);
                        intent.putExtra("fragment", "MainPageFragment");
                        startActivity(intent);
                        finish();
                        drawerLayout.closeDrawer(Gravity.LEFT);

                        break;
                    case R.id.nav_profile:
                        drawerLayout.closeDrawer(Gravity.LEFT);
                        break;
                    case R.id.nav_visit:
                        intent = new Intent(UserActivityInfo.this, MainPageActivity.class);
                        intent.putExtra("fragment", "VisitFragment");
                        startActivity(intent);
                        finish();
                        drawerLayout.closeDrawer(Gravity.LEFT);

                        break;
                    case R.id.nav_calendar:
                        drawerLayout.closeDrawer(Gravity.LEFT);
                        intent = new Intent(UserActivityInfo.this, CalendarActivity.class);
                        startActivity(intent);
                        finish();

                        break;
                    case R.id.nav_message:
//                        intent = new Intent(UserActivityInfo.this, MainPageActivity.class);
//                        intent.putExtra("fragment", "ChatFragment");
//                        startActivity(intent);
//                        finish();
//                        drawerLayout.closeDrawer(Gravity.LEFT);
                        Intent intentMess = new Intent(UserActivityInfo.this, ChatActivityShowAllChat.class);
                        startActivity(intentMess);
                        finish();
                        break;
                }
                return false;
            }
        });
    }

    public void cancelANDeditBtnClickListener() {
        cancelBtnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickEditUserProfile = false;
                hideKeyboard(UserActivityInfo.this);
                cancelBtnEdit.setVisibility(View.GONE);
                edit_TV_UserActivity.setText("Редактировать");
                mToggle.setDrawerIndicatorEnabled(true);
//                toolbar_title.setPadding(0, 0, 94, 0);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setHomeButtonEnabled(true);
                pagerAdapterUserInfo.notifyDataSetChanged();

            }
        });
        edit_TV_UserActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (clickEditUserProfile) {
                    clickEditUserProfile = false;
                    hideKeyboard(UserActivityInfo.this);
                    clickBtnSaveProfileEdit.performClick();
                    cancelBtnEdit.setVisibility(View.GONE);
                    edit_TV_UserActivity.setText("Редактировать");
                    setTitle("Аккаунт");
                    mToggle.setDrawerIndicatorEnabled(true);
//                    toolbar_title.setPadding(0, 0, 94, 0);
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    getSupportActionBar().setHomeButtonEnabled(true);

                } else {
                    clickEditUserProfile = true;
                    pagerAdapterUserInfo.notifyDataSetChanged();
                    edit_TV_UserActivity.setText("Сохранить");
//                    toolbar_title.setPadding(0, 0, 146, 0);
                    mToggle.setDrawerIndicatorEnabled(false);
                    setTitle("Аккаунт");
                    getSupportActionBar().setDisplayShowHomeEnabled(false);
                    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                    getSupportActionBar().setHomeButtonEnabled(false);
                    cancelBtnEdit.setVisibility(View.VISIBLE);
                }


//                dialogFragmentFilterFeed = new DialogFragmentEditUserProfile();
//                dialogFragmentFilterFeed.show(getSupportFragmentManager(), "dialogFragmentFilterFeed");


            }
        });

    }

    public void seeSubscriptions() {
        App.getApi().allUserSubscpriptionsToDoc(GlobalVariables.user_auth_token).enqueue(new Callback<List<ResponseAllSubscriptionsToDoctor>>() {
            @Override
            public void onResponse(Call<List<ResponseAllSubscriptionsToDoctor>> call, Response<List<ResponseAllSubscriptionsToDoctor>> response) {
                int s = response.code();
                registeredUserSubscriptions = response.body();
                if (pagerAdapterUserInfo != null) {
                    pagerAdapterUserInfo.OnResponseSuccessAllSubscription(registeredUserSubscriptions);
                }
            }

            @Override
            public void onFailure(Call<List<ResponseAllSubscriptionsToDoctor>> call, Throwable t) {
                Toast.makeText(UserActivityInfo.this, "An error occurred during networking", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void seeUserProgress() {
        App.getApi().seeUserProgress(GlobalVariables.user_auth_token).enqueue(new Callback<ResponseProgressUser>() {
            @Override
            public void onResponse(Call<ResponseProgressUser> call, Response<ResponseProgressUser> response) {
                int s = response.code();
                registeredUserProgress = response.body();
                if (pagerAdapterUserInfo != null) {
                    pagerAdapterUserInfo.OnResponseSuccessProgress(registeredUserProgress);
                }
            }

            @Override
            public void onFailure(Call<ResponseProgressUser> call, Throwable t) {
                Toast.makeText(UserActivityInfo.this, "An error occurred during networking", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void seeUserFamilyMembers() {
        App.getApi().seeFamilyMembers(GlobalVariables.user_auth_token).enqueue(new Callback<ResponseMyFamilyMembers>() {
            @Override
            public void onResponse(Call<ResponseMyFamilyMembers> call, Response<ResponseMyFamilyMembers> response) {
                int s = response.code();
                registeredUserMyFamily = response.body();
                if (pagerAdapterUserInfo != null) {
                    pagerAdapterUserInfo.OnResponseSuccesMyfamily(registeredUserMyFamily);
                }
            }

            @Override
            public void onFailure(Call<ResponseMyFamilyMembers> call, Throwable t) {
                Toast.makeText(UserActivityInfo.this, "An error occurred during networking", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void seeUserInfo() {
        App.getApi().seedUserInfo(GlobalVariables.user_auth_token, GlobalVariables.user_id).enqueue(new Callback<ResponseGetUserData>() {
            @Override
            public void onResponse(Call<ResponseGetUserData> call, Response<ResponseGetUserData> response) {
                int s = response.code();
                if (response.errorBody() != null) {
                    try {
                        Log.e("LOG", "Retrofit Response: " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                registeredUser = response.body();

                if (registeredUser != null) {
                    responseGetUserData = registeredUser;
                    if (registeredUser.getPhoto() != null) {
                        MainController.setImageToViewById(UserActivityInfo.this, registeredUser.getPhoto(), profile_imageINFO);
                        profile_imageINFO.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                MainController.setImageToViewById(UserActivityInfo.this, registeredUser.getPhoto(), profile_imageINFO);
                            }
                        });
                    }

                    name_surnameTVINFO.setText(registeredUser.getName() + " " + registeredUser.getSurname());

                    first_name.setText(registeredUser.getName());
                    last_name.setText(registeredUser.getSurname());


                    if (pagerAdapterUserInfo != null) {
                        pagerAdapterUserInfo.onResponseSuccess(registeredUser);
                    }
                    pagerAdapterUserInfo.notifyDataSetChanged();

                    Toast.makeText(UserActivityInfo.this, "Загрузка прошла успешно ", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(UserActivityInfo.this, "Ошибка при загрузке ", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ResponseGetUserData> call, Throwable t) {
                Toast.makeText(UserActivityInfo.this, "An error occurred during networking", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return mToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    public ResponseGetUserData getData() {
        if (registeredUser != null)
            return registeredUser;
        return null;
    }

    @Override
    public List<ResponseAllSubscriptionsToDoctor> getDataSub() {
        if (registeredUserSubscriptions != null)
            return registeredUserSubscriptions;
        return null;
    }

    @Override
    public ResponseProgressUser getDataProgress() {
        if (registeredUserProgress != null)
            return registeredUserProgress;
        return null;
    }

    @Override
    public ResponseMyFamilyMembers getDataFamily() {
        if (registeredUserMyFamily != null)
            return registeredUserMyFamily;
        return null;
    }

    public void hideKeyboard(Context ctx) {
        InputMethodManager inputManager = (InputMethodManager) ctx
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        // check if no view has focus:
        View v = ((Activity) ctx).getCurrentFocus();
        if (v == null)
            return;

        inputManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    @Override
    public void onBackPressed() {
        if (backBtnClicked) {
            super.onBackPressed();
            finish();
        } else {
            backBtnClicked = true;
            Toast.makeText(getApplicationContext(), "Нажмите еще раз если хотите выйти", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    backBtnClicked = false;
                }
            }, App.getInstance().BackPressTreshHold);
        }

    }


}





