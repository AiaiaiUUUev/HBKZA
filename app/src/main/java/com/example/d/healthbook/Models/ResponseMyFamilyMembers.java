package com.example.d.healthbook.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by D on 05.07.2017.
 */

public class ResponseMyFamilyMembers {

    @SerializedName("page_size")
    @Expose
    private Integer pageSize;
    @SerializedName("pages")
    @Expose
    private Integer pages;
    @SerializedName("numfound")
    @Expose
    private Integer numfound;
    @SerializedName("documents")
    @Expose
    private List<DocumentMyFamily> documents = null;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Integer getNumfound() {
        return numfound;
    }

    public void setNumfound(Integer numfound) {
        this.numfound = numfound;
    }

    public List<DocumentMyFamily> getDocuments() {
        return documents;
    }

    public void setDocuments(List<DocumentMyFamily> documents) {
        this.documents = documents;
    }

}