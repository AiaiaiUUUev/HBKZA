package com.example.d.healthbook.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by D on 19.06.2017.
 */

public class DocumentDiseaseCategory {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("name_ru")
    @Expose
    private String nameRu;
    @SerializedName("name_kk")
    @Expose
    private String nameKk;
    @SerializedName("name_en")
    @Expose
    private String nameEn;
    @SerializedName("body_ru")
    @Expose
    private String bodyRu;
    @SerializedName("body_kk")
    @Expose
    private String bodyKk;
    @SerializedName("body_en")
    @Expose
    private String bodyEn;
    @SerializedName("preview_ru")
    @Expose
    private String previewRu;
    @SerializedName("preview_kk")
    @Expose
    private String previewKk;
    @SerializedName("preview_en")
    @Expose
    private String previewEn;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getNameRu() {
        return nameRu;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    public String getNameKk() {
        return nameKk;
    }

    public void setNameKk(String nameKk) {
        this.nameKk = nameKk;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getBodyRu() {
        return bodyRu;
    }

    public void setBodyRu(String bodyRu) {
        this.bodyRu = bodyRu;
    }

    public String getBodyKk() {
        return bodyKk;
    }

    public void setBodyKk(String bodyKk) {
        this.bodyKk = bodyKk;
    }

    public String getBodyEn() {
        return bodyEn;
    }

    public void setBodyEn(String bodyEn) {
        this.bodyEn = bodyEn;
    }

    public String getPreviewRu() {
        return previewRu;
    }

    public void setPreviewRu(String previewRu) {
        this.previewRu = previewRu;
    }

    public String getPreviewKk() {
        return previewKk;
    }

    public void setPreviewKk(String previewKk) {
        this.previewKk = previewKk;
    }

    public String getPreviewEn() {
        return previewEn;
    }

    public void setPreviewEn(String previewEn) {
        this.previewEn = previewEn;
    }

}