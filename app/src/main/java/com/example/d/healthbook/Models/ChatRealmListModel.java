package com.example.d.healthbook.Models;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by D on 19.07.2017.
 */

public class ChatRealmListModel extends RealmObject {

    private String nameSurname;
    private int type;
    private RealmList<ChatModel> chatModels;
    private RealmList<GroupModel> groupModel;
    private int ImageMan;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public int getImageMan() {
        return ImageMan;
    }

    public void setImageMan(int imageMan) {
        ImageMan = imageMan;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    public String getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }


    public RealmList<GroupModel> getGroupModel() {
        return groupModel;
    }

    public void setGroupModel(RealmList<GroupModel> groupModel) {
        this.groupModel = groupModel;
    }


    public RealmList<ChatModel> getChatModels() {
        return chatModels;
    }

    public void setChatModels(RealmList<ChatModel> chatModels) {
        this.chatModels = chatModels;
    }
}
