package com.example.d.healthbook.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by D on 16.06.2017.
 */

public class DocumentProtocol {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name_ru")
    @Expose
    private String nameRu;
    @SerializedName("name_kk")
    @Expose
    private String nameKk;
    @SerializedName("name_en")
    @Expose
    private String nameEn;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
