package com.example.d.healthbook.Models;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by D on 28.06.2017.
 */

public class ChatModel extends RealmObject {


    private boolean seeOrNotMessage;
    private int type;
    private String textMessage;

    public Integer getImageMan() {
        return imageMan;
    }

    public void setImageMan(Integer imageMan) {
        this.imageMan = imageMan;
    }

    private Integer imageMan;



    public ChatModel(int type, String textMessage, boolean seeOrNotMessage) {
        this.type = type;
        this.textMessage = textMessage;
        this.seeOrNotMessage = seeOrNotMessage;
        this.imageMan=0;
    }

    public ChatModel(int type, String textMessage, boolean seeOrNotMessage,Integer img) {
        this.type = type;
        this.textMessage = textMessage;
        this.seeOrNotMessage = seeOrNotMessage;
        this.imageMan=img;
    }




    public ChatModel() {
        this.type = 0;
        this.textMessage = "";
        this.seeOrNotMessage = false;
    }


    public boolean isSeeOrNotMessage() {
        return seeOrNotMessage;
    }

    public void setSeeOrNotMessage(boolean seeOrNotMessage) {
        this.seeOrNotMessage = seeOrNotMessage;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }
}
