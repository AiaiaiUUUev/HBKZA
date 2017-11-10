package com.example.d.healthbook.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by D on 03.07.2017.
 */

public class ResponseAllSubscriptionsToDoctor {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("user_id")
    @Expose
    private Object userId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("surname")
    @Expose
    private Object surname;
    @SerializedName("speciality")
    @Expose
    private Object speciality;
    @SerializedName("speciality_id")
    @Expose
    private Object specialityId;
    @SerializedName("speciality_name")
    @Expose
    private Object specialityName;
    @SerializedName("city_id")
    @Expose
    private Object cityId;
    @SerializedName("rate")
    @Expose
    private Object rate;
    @SerializedName("experience")
    @Expose
    private String experience;
    @SerializedName("city_name")
    @Expose
    private Object cityName;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("gender")
    @Expose
    private Object gender;
    @SerializedName("company_id")
    @Expose
    private Object companyId;
    @SerializedName("company_point_id")
    @Expose
    private Object companyPointId;
    @SerializedName("rooms_json")
    @Expose
    private Object roomsJson;
    @SerializedName("price")
    @Expose
    private Object price;
    @SerializedName("info")
    @Expose
    private Object info;
    @SerializedName("mention_count")
    @Expose
    private Object mentionCount;
    @SerializedName("schedule")
    @Expose
    private Object schedule;
    @SerializedName("rooms")
    @Expose
    private Object rooms;
    @SerializedName("performance_rate")
    @Expose
    private Object performanceRate;
    @SerializedName("efficiency_rate")
    @Expose
    private Object efficiencyRate;
    @SerializedName("recommendation_rate")
    @Expose
    private Object recommendationRate;
    @SerializedName("photo_ds")
    @Expose
    private Object photoDs;
    @SerializedName("photo_ds_slider")
    @Expose
    private Object photoDsSlider;
    @SerializedName("info_ds")
    @Expose
    private Object infoDs;
    @SerializedName("is_ds")
    @Expose
    private Object isDs;
    @SerializedName("rating_ds")
    @Expose
    private Object ratingDs;
    @SerializedName("clinic_name")
    @Expose
    private Object clinicName;
    @SerializedName("clinic_price")
    @Expose
    private Object clinicPrice;
    @SerializedName("clinic_discount")
    @Expose
    private Object clinicDiscount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getUserId() {
        return userId;
    }

    public void setUserId(Object userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getSurname() {
        return surname;
    }

    public void setSurname(Object surname) {
        this.surname = surname;
    }

    public Object getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Object speciality) {
        this.speciality = speciality;
    }

    public Object getSpecialityId() {
        return specialityId;
    }

    public void setSpecialityId(Object specialityId) {
        this.specialityId = specialityId;
    }

    public Object getSpecialityName() {
        return specialityName;
    }

    public void setSpecialityName(Object specialityName) {
        this.specialityName = specialityName;
    }

    public Object getCityId() {
        return cityId;
    }

    public void setCityId(Object cityId) {
        this.cityId = cityId;
    }

    public Object getRate() {
        return rate;
    }

    public void setRate(Object rate) {
        this.rate = rate;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public Object getCityName() {
        return cityName;
    }

    public void setCityName(Object cityName) {
        this.cityName = cityName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Object getGender() {
        return gender;
    }

    public void setGender(Object gender) {
        this.gender = gender;
    }

    public Object getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Object companyId) {
        this.companyId = companyId;
    }

    public Object getCompanyPointId() {
        return companyPointId;
    }

    public void setCompanyPointId(Object companyPointId) {
        this.companyPointId = companyPointId;
    }

    public Object getRoomsJson() {
        return roomsJson;
    }

    public void setRoomsJson(Object roomsJson) {
        this.roomsJson = roomsJson;
    }

    public Object getPrice() {
        return price;
    }

    public void setPrice(Object price) {
        this.price = price;
    }

    public Object getInfo() {
        return info;
    }

    public void setInfo(Object info) {
        this.info = info;
    }

    public Object getMentionCount() {
        return mentionCount;
    }

    public void setMentionCount(Object mentionCount) {
        this.mentionCount = mentionCount;
    }

    public Object getSchedule() {
        return schedule;
    }

    public void setSchedule(Object schedule) {
        this.schedule = schedule;
    }

    public Object getRooms() {
        return rooms;
    }

    public void setRooms(Object rooms) {
        this.rooms = rooms;
    }

    public Object getPerformanceRate() {
        return performanceRate;
    }

    public void setPerformanceRate(Object performanceRate) {
        this.performanceRate = performanceRate;
    }

    public Object getEfficiencyRate() {
        return efficiencyRate;
    }

    public void setEfficiencyRate(Object efficiencyRate) {
        this.efficiencyRate = efficiencyRate;
    }

    public Object getRecommendationRate() {
        return recommendationRate;
    }

    public void setRecommendationRate(Object recommendationRate) {
        this.recommendationRate = recommendationRate;
    }

    public Object getPhotoDs() {
        return photoDs;
    }

    public void setPhotoDs(Object photoDs) {
        this.photoDs = photoDs;
    }

    public Object getPhotoDsSlider() {
        return photoDsSlider;
    }

    public void setPhotoDsSlider(Object photoDsSlider) {
        this.photoDsSlider = photoDsSlider;
    }

    public Object getInfoDs() {
        return infoDs;
    }

    public void setInfoDs(Object infoDs) {
        this.infoDs = infoDs;
    }

    public Object getIsDs() {
        return isDs;
    }

    public void setIsDs(Object isDs) {
        this.isDs = isDs;
    }

    public Object getRatingDs() {
        return ratingDs;
    }

    public void setRatingDs(Object ratingDs) {
        this.ratingDs = ratingDs;
    }

    public Object getClinicName() {
        return clinicName;
    }

    public void setClinicName(Object clinicName) {
        this.clinicName = clinicName;
    }

    public Object getClinicPrice() {
        return clinicPrice;
    }

    public void setClinicPrice(Object clinicPrice) {
        this.clinicPrice = clinicPrice;
    }

    public Object getClinicDiscount() {
        return clinicDiscount;
    }

    public void setClinicDiscount(Object clinicDiscount) {
        this.clinicDiscount = clinicDiscount;
    }

}
