package com.example.d.healthbook.RealmModels;

import io.realm.RealmObject;

/**
 * Created by D on 14.06.2017.
 */

public class ClickedFilterItems extends RealmObject {
    private String titleClicnic;
    private String cityName;

    public String getTitleClicnic() {
        return titleClicnic;
    }

    public void setTitleClicnic(String titleClicnic) {
        this.titleClicnic = titleClicnic;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    private String speciality;
}
