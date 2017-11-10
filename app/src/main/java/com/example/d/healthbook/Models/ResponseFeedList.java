package com.example.d.healthbook.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by D on 15.06.2017.
 */

public class ResponseFeedList {

    @SerializedName("pages")
    @Expose
    private Integer pages;
    @SerializedName("numFound")
    @Expose
    private Integer numFound;
    @SerializedName("documents")
    @Expose
    private List<FeedDocument> documents = null;

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Integer getNumFound() {
        return numFound;
    }

    public void setNumFound(Integer numFound) {
        this.numFound = numFound;
    }

    public List<FeedDocument> getDocuments() {
        return documents;
    }

    public void setDocuments(List<FeedDocument> documents) {
        this.documents = documents;
    }

}


