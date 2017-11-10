package com.example.d.healthbook.Activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.d.healthbook.Adapters.RecyclerChangebaleRowAllChatGroup;
import com.example.d.healthbook.Appi.App;
import com.example.d.healthbook.CalendarBekarysa.CalendarActivity;
import com.example.d.healthbook.Controller.MainController;
import com.example.d.healthbook.Models.ChatModel;
import com.example.d.healthbook.Models.ChatRealmListModel;
import com.example.d.healthbook.Models.GroupModel;
import com.example.d.healthbook.R;
import com.example.d.healthbook.UI.MyToolbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import io.realm.Sort;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ChatActivityShowAllChat extends AppCompatActivity {
    private MyToolbar toolbar;
    private NavigationView navigation;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mToggle;
    @BindView(R.id.toolbar_title)
    TextView toolbar_title;
    @BindView(R.id.add_one_man_chat)
    ImageView add_one_man_chat;
    @BindView(R.id.add_group_man_chat)
    ImageView add_group_man_chat;
    @BindView(R.id.createGroupBtn)
    LinearLayout createGroupBtn;
    @BindView(R.id.containerETLL)
    LinearLayout containerETLL;
    @BindView(R.id.titleET)
    EditText titleET;
    private Intent intent;
    private RealmList<ChatModel> chatModels;
    private Realm mRealm;
    private RecyclerView mRecyclerViewChat;
    private RecyclerChangebaleRowAllChatGroup adapter;
    private static String titleGroup = "";
    public static List<ChatModel> chatModelsAddOneMan;
    private String groupPeople = "";
    private String addpeople;
    private int position = 0;
    private int IMG;
    private RealmResults<ChatRealmListModel> listModels;
    private boolean backBtnClicked;


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_show_all_chat);
        ButterKnife.bind(this);

        mRealm = Realm.getInstance(this);

        toolbar = (MyToolbar) findViewById(R.id.main_page_toolbar);
        navigation = (NavigationView) findViewById(R.id.navigationView);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        mRecyclerViewChat = (RecyclerView) findViewById(R.id.reccyclerChat);
        mRecyclerViewChat.setLayoutManager(new LinearLayoutManager(this));


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToggle.setDrawerIndicatorEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu_black_24x24);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        toolbar_title.setText("Добавить группу для чата");
        navigationClickItemListener();
        navigation.getMenu().getItem(3).setChecked(true);


        addItemRecycler();
        setRealmChatToRecycler();
        position = getIntent().getIntExtra("position", -1);
        groupPeople = getIntent().getStringExtra("group");
        addpeople = getIntent().getStringExtra("addpeople");
        IMG = getIntent().getIntExtra("IMG", -1);

        if (groupPeople != null) {
            setGroupNewGroupORChatMethod(false, "", 0, chatModels);
        } else if (addpeople != null) {
            setGroupNewGroupORChatMethod(true, addpeople, IMG, chatModelsAddOneMan);
        } else if (position != -1) {
            addChatModel(position);
        }


        add_one_man_chat.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(ChatActivityShowAllChat.this, ChatActivityShowMessage.class);
//                startActivity(intent);
                Intent intent = new Intent(ChatActivityShowAllChat.this, ChatActivityChooseManForGroup.class);
                intent.putExtra("addOne", true);
                startActivity(intent);
            }
        });

        add_group_man_chat.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v) {
                toolbar_title.setText("Название чата");
                add_one_man_chat.setVisibility(View.GONE);
                add_group_man_chat.setVisibility(View.GONE);
                mToggle.setDrawerIndicatorEnabled(false);
                createGroupBtn.setVisibility(View.VISIBLE);
                containerETLL.setVisibility(View.VISIBLE);

                titleET.setText("");
                titleET.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(titleET, InputMethodManager.SHOW_IMPLICIT);

                titleET.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_NEXT) {
                            createGroupBtn.performClick();
                            return true;
                        }
                        return false;
                    }
                });
                createGroupBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        titleGroup = titleET.getText().toString();
                        if (!titleGroup.equals("")) {
                            Intent intent = new Intent(ChatActivityShowAllChat.this, ChatActivityChooseManForGroup.class);
                            startActivity(intent);

                        } else {
                            MainController.showToast(ChatActivityShowAllChat.this,
                                    "Введите название группы");
                        }

                    }
                });

            }
        });
    }

    private void addChatModel(int position) {
        mRealm.beginTransaction();
        listModels.get(position).getChatModels().clear();
        mRealm.commitTransaction();
        for (int i = 0; i < chatModelsAddOneMan.size(); i++) {
            mRealm.beginTransaction();
            listModels.get(position).getChatModels().add(chatModelsAddOneMan.get(i));
            mRealm.commitTransaction();
        }
        adapter.notifyDataSetChanged();
    }


    public void setGroupNewGroupORChatMethod(boolean addOne, String addpeople, Integer IMG, List<ChatModel> chatModels) {

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

        if (!addOne) {
            GroupModel groupModel = new GroupModel(groupPeople, titleGroup);
            mRealm.beginTransaction();
            ChatRealmListModel model2 = mRealm.createObject(ChatRealmListModel.class);
            model2.setType(2);
            model2.setDate(timeStamp);
            model2.getGroupModel().add(groupModel);
            mRealm.commitTransaction();
        } else {
            mRealm.beginTransaction();
            ChatRealmListModel model2 = mRealm.createObject(ChatRealmListModel.class);
            for (int i = 0; i < chatModels.size(); i++) {
                model2.getChatModels().add(chatModels.get(i));
            }
            model2.setDate(timeStamp);
            model2.setNameSurname(addpeople);
            model2.setImageMan(IMG);
            model2.setType(1);
            mRealm.commitTransaction();
        }


        listModels.sort("date", Sort.DESCENDING);
        adapter.notifyDataSetChanged();
    }

    private void addItemRecycler() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

        chatModels = new RealmList<>();
        ChatModel chatModel = new ChatModel(1, "Добрый день, могу я записаться к вам на прием?", true);
        ChatModel chatModel1 = new ChatModel(2, "Здравствуйте конечно, завтра с 9 до 18 принимаю", true);
        ChatModel chatModel2 = new ChatModel(1, "А сейчас посоветуйте что нибудь от аллергии выпить", true);
        ChatModel chatModel3 = new ChatModel(2, "Супростин", false);
        ChatModel chatModel4 = new ChatModel(2, "Спасибо до завтра!", false);

        ChatModel chatMode01 = new ChatModel(1, "Здравствуйте я насчет операции на сосуды узнать хочу", true);
        ChatModel chatMode02 = new ChatModel(2, "Здравствуйте, а что именно?", true);
        ChatModel chatMode03 = new ChatModel(1, "Варикоз на ноге", true);
        ChatModel chatMode04 = new ChatModel(2, "Да это лечим без проблем, приходите мы вас посмотрим", false);
        ChatModel chatMode05 = new ChatModel(2, "Спасибо до встречи", false);
        ChatModel chatMode06 = new ChatModel(2, "Досвидание", false);

        RealmResults<ChatRealmListModel> listModels = mRealm.allObjects(ChatRealmListModel.class);
        if (listModels.isEmpty()) {
            mRealm.beginTransaction();
            ChatRealmListModel model = mRealm.createObject(ChatRealmListModel.class);
            model.setNameSurname("Митковская Оксана");
            model.setType(1);
            model.setDate(timeStamp);
            model.setImageMan(R.drawable.img_mitkovskaya);
            model.getChatModels().add(chatModel);
            model.getChatModels().add(chatModel1);
            model.getChatModels().add(chatModel2);
            model.getChatModels().add(chatModel3);
            model.getChatModels().add(chatModel4);


            mRealm.commitTransaction();

            mRealm.beginTransaction();
            ChatRealmListModel model2 = mRealm.createObject(ChatRealmListModel.class);
            model2.setNameSurname("Хвостиков Евгений");
            model2.setDate(timeStamp);
            model2.setType(1);
            model2.setImageMan(R.drawable.img_hvostikov);
            model2.getChatModels().add(chatMode01);
            model2.getChatModels().add(chatMode02);
            model2.getChatModels().add(chatMode03);
            model2.getChatModels().add(chatMode04);
            model2.getChatModels().add(chatMode05);
            model2.getChatModels().add(chatMode06);


            mRealm.commitTransaction();
        }
    }

    private void setRealmChatToRecycler() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

        listModels = mRealm.allObjects(ChatRealmListModel.class);
        listModels.sort("date", Sort.DESCENDING);
        adapter = new RecyclerChangebaleRowAllChatGroup(listModels, ChatActivityShowAllChat.this);
        mRecyclerViewChat.setAdapter(adapter);

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
                        intent = new Intent(ChatActivityShowAllChat.this, MainPageActivity.class);
                        intent.putExtra("fragment", "IndicatorDiaryFragment");
                        startActivity(intent);
                        finish();
                        drawerLayout.closeDrawer(Gravity.LEFT);

                        break;
                    case R.id.nav_search_doctors:
                        intent = new Intent(ChatActivityShowAllChat.this, MainPageActivity.class);
                        intent.putExtra("fragment", "MainPageFragment");
                        startActivity(intent);
                        finish();
                        drawerLayout.closeDrawer(Gravity.LEFT);

                        break;
                    case R.id.nav_profile:
                        Intent intent = new Intent(ChatActivityShowAllChat.this, UserActivityInfo.class);
                        startActivity(intent);
                        finish();
                        drawerLayout.closeDrawer(Gravity.LEFT);
                        break;
                    case R.id.nav_visit:
                        intent = new Intent(ChatActivityShowAllChat.this, MainPageActivity.class);
                        intent.putExtra("fragment", "VisitFragment");
                        startActivity(intent);
                        finish();
                        drawerLayout.closeDrawer(Gravity.LEFT);

                        break;
                    case R.id.nav_calendar:
                        drawerLayout.closeDrawer(Gravity.LEFT);
                        intent = new Intent(ChatActivityShowAllChat.this, CalendarActivity.class);
                        startActivity(intent);
                        finish();

                        break;
                    case R.id.nav_message:

                        drawerLayout.closeDrawer(Gravity.LEFT);
                        break;
                }
                return false;
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        } else {
            switch (item.getItemId()) {
                case android.R.id.home:
                    toolbar_title.setText("Health book");
                    add_one_man_chat.setVisibility(View.VISIBLE);
                    add_group_man_chat.setVisibility(View.VISIBLE);
                    createGroupBtn.setVisibility(View.GONE);
                    containerETLL.setVisibility(View.GONE);


                    mToggle.setDrawerIndicatorEnabled(true);
                    getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu_black_24x24);
                    hideKeyboard2(ChatActivityShowAllChat.this);
                    return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    public static void hideKeyboard2(Context ctx) {
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
