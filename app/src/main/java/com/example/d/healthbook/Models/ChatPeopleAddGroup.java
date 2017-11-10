package com.example.d.healthbook.Models;

/**
 * Created by D on 19.07.2017.
 */

public class ChatPeopleAddGroup {
    private boolean NowSelected;
    private String name;
    private boolean selected;
    private int Image;


    public ChatPeopleAddGroup(String name, boolean selected, boolean isSelected, int drawable) {
        this.name = name;
        this.selected = selected;
        this.NowSelected = isSelected;
        this.Image = drawable;
    }

    public boolean isNowSelected() {
        return NowSelected;
    }

    public void setNowSelected(boolean nowSelected) {
        this.NowSelected = nowSelected;
    }


    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }


    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }


    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
