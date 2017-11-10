package com.example.d.healthbook.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by D on 19.06.2017.
 */

public class ListChannel {

    @SerializedName("type")
    @Expose
    private Object type;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image")
    @Expose
    private Object image;
    @SerializedName("image_small")
    @Expose
    private Object imageSmall;
    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("desc_kaz")
    @Expose
    private String descKaz;
    @SerializedName("desc_eng")
    @Expose
    private String descEng;

    public Object getType() {
        return type;
    }

    public void setType(Object type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }

    public Object getImageSmall() {
        return imageSmall;
    }

    public void setImageSmall(Object imageSmall) {
        this.imageSmall = imageSmall;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDescKaz() {
        return descKaz;
    }

    public void setDescKaz(String descKaz) {
        this.descKaz = descKaz;
    }

    public String getDescEng() {
        return descEng;
    }

    public void setDescEng(String descEng) {
        this.descEng = descEng;
    }

}