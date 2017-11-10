package com.example.d.healthbook.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by D on 31.05.2017.
 */

public class Document {



    @SerializedName("text")
    @Expose
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @SerializedName("type")
    @Expose
    private Integer type;

    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("channel_id")
    @Expose
    private String channelId;
    @SerializedName("category_name")
    @Expose
    private String categoryName;
    @SerializedName("channel_name")
    @Expose
    private String channelName;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("preview")
    @Expose
    private String preview;
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("created_time")
    @Expose
    private String createdTime;
    @SerializedName("updated_time")
    @Expose
    private String updatedTime;
    @SerializedName("language")
    @Expose
    private String language;
    @SerializedName("votes")
    @Expose
    private Object votes;
    @SerializedName("views")
    @Expose
    private Object views;
    @SerializedName("tags")
    @Expose
    private Object tags;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Object getVotes() {
        return votes;
    }

    public void setVotes(Object votes) {
        this.votes = votes;
    }

    public Object getViews() {
        return views;
    }

    public void setViews(Object views) {
        this.views = views;
    }

    public Object getTags() {
        return tags;
    }

    public void setTags(Object tags) {
        this.tags = tags;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImageSmall() {
        return imageSmall;
    }

    public void setImageSmall(String imageSmall) {
        this.imageSmall = imageSmall;
    }

    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("image_small")
    @Expose
    private String imageSmall;


    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("address")
    @Expose
    private String address;


    @SerializedName("emails")
    @Expose
    private String emails;
    @SerializedName("phones")
    @Expose
    private String phones;
    @SerializedName("longitude")
    @Expose
    private Object longitude;
    @SerializedName("latitude")
    @Expose
    private Object latitude;
    @SerializedName("mentions")
    @Expose
    private List<Mention> mentions = null;

    @SerializedName("quality_price_rate")
    @Expose
    private Object qualityPriceRate;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    public String getPhones() {
        return phones;
    }

    public void setPhones(String phones) {
        this.phones = phones;
    }

    public Object getLongitude() {
        return longitude;
    }

    public void setLongitude(Object longitude) {
        this.longitude = longitude;
    }

    public Object getLatitude() {
        return latitude;
    }

    public void setLatitude(Object latitude) {
        this.latitude = latitude;
    }

    public List<Mention> getMentions() {
        return mentions;
    }

    public void setMentions(List<Mention> mentions) {
        this.mentions = mentions;
    }

    public Object getQualityPriceRate() {
        return qualityPriceRate;
    }

    public void setQualityPriceRate(Object qualityPriceRate) {
        this.qualityPriceRate = qualityPriceRate;
    }

    public Boolean getDs() {
        return isDs;
    }

    public void setDs(Boolean ds) {
        isDs = ds;
    }

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("user_id")
    @Expose
    private Integer userId;







    @SerializedName("name")
    @Expose
    private String name;






    @SerializedName("surname")
    @Expose
    private String surname;
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
    private Integer rate;
    @SerializedName("experience")
    @Expose
    private String experience;
    @SerializedName("city_name")
    @Expose
    private Object cityName;
    @SerializedName("photo")
    @Expose
    private Object photo;
    @SerializedName("gender")
    @Expose
    private String gender;
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
    private Integer mentionCount;
    @SerializedName("schedule")
    @Expose
    private List<Schedule> schedule = null;
    @SerializedName("rooms")
    @Expose
    private List<Room> rooms = null;
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
    private String infoDs;
    @SerializedName("is_ds")
    @Expose
    private Boolean isDs;
    @SerializedName("rating_ds")
    @Expose
    private Integer ratingDs;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }






    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }










    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
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

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
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

    public Object getPhoto() {
        return photo;
    }

    public void setPhoto(Object photo) {
        this.photo = photo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
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

    public Integer getMentionCount() {
        return mentionCount;
    }

    public void setMentionCount(Integer mentionCount) {
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

    public String getInfoDs() {
        return infoDs;
    }

    public void setInfoDs(String infoDs) {
        this.infoDs = infoDs;
    }

    public Boolean getIsDs() {
        return isDs;
    }

    public void setIsDs(Boolean isDs) {
        this.isDs = isDs;
    }

    public Integer getRatingDs() {
        return ratingDs;
    }

    public void setRatingDs(Integer ratingDs) {
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