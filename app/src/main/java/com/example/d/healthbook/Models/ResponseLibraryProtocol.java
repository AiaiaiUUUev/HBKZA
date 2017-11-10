package com.example.d.healthbook.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by D on 16.06.2017.
 */

public class ResponseLibraryProtocol {

    @SerializedName("pages")
    @Expose
    private Integer pages;
    @SerializedName("numfound")
    @Expose
    private String numfound;
    @SerializedName("documents")
    @Expose
    private List<DocumentProtocol> documents = null;

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public String getNumfound() {
        return numfound;
    }

    public void setNumfound(String numfound) {
        this.numfound = numfound;
    }

    public List<DocumentProtocol> getDocuments() {
        return documents;
    }

    public void setDocuments(List<DocumentProtocol> documents) {
        this.documents = documents;
    }

}

