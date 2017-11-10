package com.example.d.healthbook.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.d.healthbook.Adapters.RecyclerAdapterChatActivity;
import com.example.d.healthbook.Controller.MainController;
import com.example.d.healthbook.Models.ChatModel;
import com.example.d.healthbook.Models.ChatRealmListModel;
import com.example.d.healthbook.R;
import com.example.d.healthbook.UI.MyToolbar;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static com.example.d.healthbook.Activities.ChatActivityShowAllChat.chatModelsAddOneMan;

/**
 * Created by D on 18.07.2017.
 */
public class ChatActivityShowMessage extends AppCompatActivity {
    private MyToolbar toolbar;
    private RecyclerView mRecyclerViewChat;
    private RecyclerAdapterChatActivity recyclerAdapterChatActivity;
    private List<ChatModel> chatModels;
    private EditText editTextChat;
    private ImageButton imageButtonSentMessage;
    private String getTextSendMessage;


    @BindView(R.id.toolbar_title)
    TextView toolbar_title;
    @BindView(R.id.profileAvaChatPreviewIV)
    RoundedImageView profileAvaChatPreviewIV;
    @BindView(R.id.people_chat_TV)
    TextView people_chat_TV;

    private int position;
    private Realm mRealm;
    private String addpeople;
    private int IMG;
    private String nameMan = "";

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_show_message);
        ButterKnife.bind(this);

        toolbar = (MyToolbar) findViewById(R.id.main_page_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_look_back);

        mRealm = Realm.getInstance(this);
        mRecyclerViewChat = (RecyclerView) findViewById(R.id.reccyclerChat);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        mRecyclerViewChat.setLayoutManager(layoutManager);

        editTextChat = (EditText) findViewById(R.id.editTextChat);
        imageButtonSentMessage = (ImageButton) findViewById(R.id.sentButton);

        boolean fromRecylcerAdapter = getIntent().getBooleanExtra("fromRecycler", false);
        position = getIntent().getIntExtra("position", 0);
        nameMan = getIntent().getStringExtra("name");
        addpeople = getIntent().getStringExtra("people");
        IMG = getIntent().getIntExtra("img", -1);

        if (fromRecylcerAdapter) {
            addItemRecyclerFromRealm();
        } else if (addpeople != null) {
            correctToolbarItems();

        }


        recyclerAdapterChatActivity = new RecyclerAdapterChatActivity(chatModels, this);
        mRecyclerViewChat.setAdapter(recyclerAdapterChatActivity);


        imageButtonSentMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getTextSendMessage = editTextChat.getText().toString();
                if (!getTextSendMessage.equals("")) {
                    ChatModel chatModel = new ChatModel(1, getTextSendMessage, true);
                    chatModels.add(chatModel);
                    recyclerAdapterChatActivity.notifyDataSetChanged();
                    mRecyclerViewChat.scrollToPosition(chatModels.size() - 1);
                    editTextChat.setText("");
                }

            }
        });

    }

    private void correctToolbarItems() {
        chatModels = new ArrayList<>();
        profileAvaChatPreviewIV.setPadding(0, 0, 0, 0);
        profileAvaChatPreviewIV.setOval(true);

        profileAvaChatPreviewIV.setImageBitmap(MainController.decodeSampledBitmapFromResource(getResources(),
                IMG, 100, 100));
        people_chat_TV.setVisibility(View.INVISIBLE);
        toolbar_title.setText(addpeople);
    }


    private void addItemRecyclerFromRealm() {
        chatModels = new ArrayList<>();
        RealmResults<ChatRealmListModel> listModels = mRealm.allObjects(ChatRealmListModel.class);
        listModels.sort("date", Sort.DESCENDING);
        if (!listModels.isEmpty()) {
            chatModels.addAll(listModels.get(position).getChatModels());
        }
        profileAvaChatPreviewIV.setPadding(0, 0, 0, 0);
        profileAvaChatPreviewIV.setOval(true);
        profileAvaChatPreviewIV.setImageBitmap(MainController.decodeSampledBitmapFromResource(getResources(),
                IMG, 100, 100));
        people_chat_TV.setVisibility(View.INVISIBLE);
        toolbar_title.setText(nameMan);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (chatModels.size() != 0) {
                    chatModelsAddOneMan = new ArrayList<>();
                    chatModelsAddOneMan.addAll(chatModels);
                    Intent intent = new Intent(ChatActivityShowMessage.this, ChatActivityShowAllChat.class);
                    if (addpeople != null) {
                        intent.putExtra("addpeople", addpeople);
                    } else {
                        intent.putExtra("name", nameMan);
                        intent.putExtra("position", position);
                    }
                    intent.putExtra("IMG", IMG);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(ChatActivityShowMessage.this, ChatActivityShowAllChat.class);
                    startActivity(intent);
                    finish();
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}