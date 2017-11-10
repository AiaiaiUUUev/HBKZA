package com.example.d.healthbook.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by D on 01.06.2017.
 */

public class ResponseDoctorInfo {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("name")
    @Expose
    private Object name;
    @SerializedName("surname")
    @Expose
    private Object surname;
    @SerializedName("speciality")
    @Expose
    private String speciality;
    @SerializedName("speciality_id")
    @Expose
    private String specialityId;
    @SerializedName("speciality_name")
    @Expose
    private String specialityName;
    @SerializedName("city_id")
    @Expose
    private String cityId;
    @SerializedName("rate")
    @Expose
    private String rate;
    @SerializedName("experience")
    @Expose
    private String experience;
    @SerializedName("city_name")
    @Expose
    private String cityName;
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
    private String info;
    @SerializedName("mention_count")
    @Expose
    private String mentionCount;
    @SerializedName("schedule")
    @Expose
    private List<Schedule> schedule = null;
    @SerializedName("rooms")
    @Expose
    private List<Room> rooms = null;
    @SerializedName("performance_rate")
    @Expose
    private String performanceRate;
    @SerializedName("efficiency_rate")
    @Expose
    private String efficiencyRate;
    @SerializedName("recommendation_rate")
    @Expose
    private String recommendationRate;
    @SerializedName("photo_ds")
    @Expose
    private String photoDs;
    @SerializedName("photo_ds_slider")
    @Expose
    private String photoDsSlider;
    @SerializedName("info_ds")
    @Expose
    private String infoDs;
    @SerializedName("is_ds")
    @Expose
    private String isDs;
    @SerializedName("rating_ds")
    @Expose
    private String ratingDs;
    @SerializedName("clinic_name")
    @Expose
    private String clinicName;
    @SerializedName("clinic_price")
    @Expose
    private Integer clinicPrice;
    @SerializedName("clinic_discount")
    @Expose
    private Integer clinicDiscount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public Object getSurname() {
        return surname;
    }

    public void setSurname(Object surname) {
        this.surname = surname;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getSpecialityId() {
        return specialityId;
    }

    public void setSpecialityId(String specialityId) {
        this.specialityId = specialityId;
    }

    public String getSpecialityName() {
        return specialityName;
    }

    public void setSpecialityName(String specialityName) {
        this.specialityName = specialityName;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getMentionCount() {
        return mentionCount;
    }

    public void setMentionCount(String mentionCount) {
        this.mentionCount = mentionCount;
    }

    public List<Schedule> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<Schedule> schedule) {
        this.schedule = schedule;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public String getPerformanceRate() {
        return performanceRate;
    }

    public void setPerformanceRate(String performanceRate) {
        this.performanceRate = performanceRate;
    }

    public String getEfficiencyRate() {
        return efficiencyRate;
    }

    public void setEfficiencyRate(String efficiencyRate) {
        this.efficiencyRate = efficiencyRate;
    }

    public String getRecommendationRate() {
        return recommendationRate;
    }

    public void setRecommendationRate(String recommendationRate) {
        this.recommendationRate = recommendationRate;
    }

    public String getPhotoDs() {
        return photoDs;
    }

    public void setPhotoDs(String photoDs) {
        this.photoDs = photoDs;
    }

    public String getPhotoDsSlider() {
        return photoDsSlider;
    }

    public void setPhotoDsSlider(String photoDsSlider) {
        this.photoDsSlider = photoDsSlider;
    }

    public String getInfoDs() {
        return infoDs;
    }

    public void setInfoDs(String infoDs) {
        this.infoDs = infoDs;
    }

    public String getIsDs() {
        return isDs;
    }

    public void setIsDs(String isDs) {
        this.isDs = isDs;
    }

    public String getRatingDs() {
        return ratingDs;
    }

    public void setRatingDs(String ratingDs) {
        this.ratingDs = ratingDs;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public Integer getClinicPrice() {
        return clinicPrice;
    }

    public void setClinicPrice(Integer clinicPrice) {
        this.clinicPrice = clinicPrice;
    }

    public Integer getClinicDiscount() {
        return clinicDiscount;
    }

    public void setClinicDiscount(Integer clinicDiscount) {
        this.clinicDiscount = clinicDiscount;
    }

}