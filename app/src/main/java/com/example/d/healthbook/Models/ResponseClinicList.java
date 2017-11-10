package com.example.d.healthbook.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by D on 07.06.2017.
 */

public class ResponseClinicList {

    @SerializedName("numfound")
    @Expose
    private Integer numfound;
    @SerializedName("pages")
    @Expose
    private Integer pages;
    @SerializedName("documents")
    @Expose
    private List<Document> documents = null;

    public Integer getNumfound() {
        return numfound;
    }

    public void setNumfound(Integer numfound) {
        this.numfound = numfound;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

}