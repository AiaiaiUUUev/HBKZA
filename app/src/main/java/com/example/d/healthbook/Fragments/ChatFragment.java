package com.example.d.healthbook.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.d.healthbook.Activities.MainPageActivity;
import com.example.d.healthbook.Adapters.RecyclerAdapterChatActivity;
import com.example.d.healthbook.Appi.App;
import com.example.d.healthbook.Models.ChatModel;
import com.example.d.healthbook.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by D on 28.06.2017.
 */

public class ChatFragment extends Fragment {
    private RecyclerView mRecyclerViewChat;
    private RecyclerAdapterChatActivity recyclerAdapterChatActivity;
    private List<ChatModel> chatModels;
    private EditText editTextChat;
    private ImageButton imageButtonSentMessage;
    private String getTextSendMessage;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);


    }

    private void seeMessages() {
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //App.getInstance().setCurrentFragment(this);
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.chat_fragment, container, false);

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        App.getInstance().changeToolbarTitle((AppCompatActivity) getActivity(), "Имя Фамилия");
//        ((MainPageActivity) getActivity()).changeMenuItems(0);
        ((MainPageActivity) getActivity()).changeImageVisibility(1);
        ((MainPageActivity) getActivity()).changeMenuItems(0);
        App.getInstance().setCurrentFragment(this);

        mRecyclerViewChat = (RecyclerView) view.findViewById(R.id.reccyclerChat);
        mRecyclerViewChat.setLayoutManager(new LinearLayoutManager(getActivity()));
        editTextChat = (EditText) view.findViewById(R.id.editTextChat);
        imageButtonSentMessage= (ImageButton) view.findViewById(R.id.sentButton);
        addItemRecycler();
        recyclerAdapterChatActivity = new RecyclerAdapterChatActivity(chatModels, getActivity());
        mRecyclerViewChat.setAdapter(recyclerAdapterChatActivity);
        imageButtonSentMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getTextSendMessage=editTextChat.getText().toString();
                if(!getTextSendMessage.equals("")){
                    ChatModel chatModel = new ChatModel();
                    chatModel.setTextMessage(getTextSendMessage);
                    chatModel.setType(1);
                    chatModels.add(chatModel);
                    recyclerAdapterChatActivity.notifyDataSetChanged();
                    mRecyclerViewChat.scrollToPosition(chatModels.size()-1);
                    editTextChat.setText("");
                }

            }
        });

    }



    private void addItemRecycler() {
        chatModels = new ArrayList<>();
        ChatModel chatModel = new ChatModel();
        chatModel.setTextMessage("Привет как дела?");
        chatModel.setType(1);
        ChatModel chatModel1 = new ChatModel();
        chatModel1.setType(2);
        chatModel1.setTextMessage("Нормально у тебя?");
        ChatModel chatModel2 = new ChatModel();
        chatModel2.setType(1);
        chatModel2.setTextMessage("замечательно");
        chatModels.add(chatModel);
        chatModels.add(chatModel1);
        chatModels.add(chatModel2);
    }


}
















