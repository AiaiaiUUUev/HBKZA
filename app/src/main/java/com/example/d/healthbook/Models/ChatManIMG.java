package com.example.d.healthbook.Models;

/**
 * Created by D on 20.07.2017.
 */

public class ChatManIMG {
    private int drawableIMG;
    private String name;

    public ChatManIMG(int drawableIMG, String name) {
        this.drawableIMG = drawableIMG;
        this.name = name;
    }

    public int getDrawableIMG() {

        return drawableIMG;
    }

    public void setDrawableIMG(int drawableIMG) {
        this.drawableIMG = drawableIMG;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
