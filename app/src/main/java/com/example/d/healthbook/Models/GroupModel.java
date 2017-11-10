package com.example.d.healthbook.Models;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by D on 20.07.2017.
 */

public class GroupModel extends RealmObject {
    private String allGroupPeople;
    private String titleGroup;
    private int typeModel;

    public String getAllGroupPeople() {
        return allGroupPeople;
    }

    public void setAllGroupPeople(String allGroupPeople) {
        this.allGroupPeople = allGroupPeople;
    }

    public String getTitleGroup() {
        return titleGroup;
    }

    public void setTitleGroup(String titleGroup) {
        this.titleGroup = titleGroup;
    }

    public int getTypeModel() {
        return typeModel;
    }

    public void setTypeModel(int typeModel) {
        this.typeModel = typeModel;
    }


    public GroupModel(String allGroupPeople, String tilte_group) {
        this.allGroupPeople = allGroupPeople;
        this.titleGroup = tilte_group;
    }

    public GroupModel() {
        this.allGroupPeople = null;
        this.titleGroup = null;

    }
}
