package com.example.d.healthbook.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by D on 16.06.2017.
 */

public class DocumentProtocolInfo {

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
    @SerializedName("protocol_ru")
    @Expose
    private String protocolRu;
    @SerializedName("protocol_kk")
    @Expose
    private String protocolKk;
    @SerializedName("protocol_en")
    @Expose
    private String protocolEn;

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

    public String getProtocolRu() {
        return protocolRu;
    }

    public void setProtocolRu(String protocolRu) {
        this.protocolRu = protocolRu;
    }

    public String getProtocolKk() {
        return protocolKk;
    }

    public void setProtocolKk(String protocolKk) {
        this.protocolKk = protocolKk;
    }

    public String getProtocolEn() {
        return protocolEn;
    }

    public void setProtocolEn(String protocolEn) {
        this.protocolEn = protocolEn;
    }

}
