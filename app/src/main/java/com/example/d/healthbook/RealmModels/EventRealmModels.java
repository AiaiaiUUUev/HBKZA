package com.example.d.healthbook.RealmModels;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * Created by D on 29.06.2017.
 */

public class EventRealmModels extends RealmObject {


    private long clickedDateRealm;
    private int type;
    private String titleEvent;
    private String descriptionEvent;



    public long getClickedDateRealm() {
        return clickedDateRealm;
    }

    public void setClickedDateRealm(long clickedDateRealm) {
        this.clickedDateRealm = clickedDateRealm;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitleEvent() {
        return titleEvent;
    }

    public void setTitleEvent(String titleEvent) {
        this.titleEvent = titleEvent;
    }

    public String getDescriptionEvent() {
        return descriptionEvent;
    }

    public void setDescriptionEvent(String descriptionEvent) {
        this.descriptionEvent = descriptionEvent;
    }
}
