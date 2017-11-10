package com.example.d.healthbook.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by D on 19.06.2017.
 */

public class ResponseChannel {


    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("pages")
    @Expose
    private Integer pages;
    @SerializedName("list")
    @Expose
    private java.util.List<ListChannel> list = null;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public java.util.List<ListChannel> getList() {
        return list;
    }

    public void setList(java.util.List<ListChannel> list) {
        this.list = list;
    }

}