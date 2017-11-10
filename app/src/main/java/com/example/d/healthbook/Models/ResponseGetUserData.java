package com.example.d.healthbook.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by D on 06.06.2017.
 */

public class ResponseGetUserData {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("createdTime")
    @Expose
    private String createdTime;
    @SerializedName("updatedTime")
    @Expose
    private String updatedTime;
    @SerializedName("password")
    @Expose
    private Object password;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("surname")
    @Expose
    private String surname;
    @SerializedName("middleName")
    @Expose
    private String middleName;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("genderId")
    @Expose
    private Integer genderId;
    @SerializedName("birthday")
    @Expose
    private String birthday;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("isActive")
    @Expose
    private Integer isActive;
    @SerializedName("height")
    @Expose
    private Integer height;
    @SerializedName("weight")
    @Expose
    private Integer weight;
    @SerializedName("allergy")
    @Expose
    private String allergy;
    @SerializedName("pastIllnesses")
    @Expose
    private String pastIllnesses;
    @SerializedName("chronicDiseases")
    @Expose
    private String chronicDiseases;
    @SerializedName("bloodGroup")
    @Expose
    private String bloodGroup;
    @SerializedName("badHabits")
    @Expose
    private Object badHabits;
    @SerializedName("heredity")
    @Expose
    private Object heredity;
    @SerializedName("homePhone")
    @Expose
    private Object homePhone;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("confirmedPhone")
    @Expose
    private Object confirmedPhone;
    @SerializedName("phoneConfirmationTime")
    @Expose
    private Object phoneConfirmationTime;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("confirmedEmail")
    @Expose
    private String confirmedEmail;
    @SerializedName("emailConfirmationTime")
    @Expose
    private Object emailConfirmationTime;
    @SerializedName("city_id")
    @Expose
    private Object cityId;
    @SerializedName("promo")
    @Expose
    private Object promo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Object getPassword() {
        return password;
    }

    public void setPassword(Object password) {
        this.password = password;
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

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getGenderId() {
        return genderId;
    }

    public void setGenderId(Integer genderId) {
        this.genderId = genderId;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getAllergy() {
        return allergy;
    }

    public void setAllergy(String allergy) {
        this.allergy = allergy;
    }

    public String getPastIllnesses() {
        return pastIllnesses;
    }

    public void setPastIllnesses(String pastIllnesses) {
        this.pastIllnesses = pastIllnesses;
    }

    public String getChronicDiseases() {
        return chronicDiseases;
    }

    public void setChronicDiseases(String chronicDiseases) {
        this.chronicDiseases = chronicDiseases;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public Object getBadHabits() {
        return badHabits;
    }

    public void setBadHabits(Object badHabits) {
        this.badHabits = badHabits;
    }

    public Object getHeredity() {
        return heredity;
    }

    public void setHeredity(Object heredity) {
        this.heredity = heredity;
    }

    public Object getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(Object homePhone) {
        this.homePhone = homePhone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Object getConfirmedPhone() {
        return confirmedPhone;
    }

    public void setConfirmedPhone(Object confirmedPhone) {
        this.confirmedPhone = confirmedPhone;
    }

    public Object getPhoneConfirmationTime() {
        return phoneConfirmationTime;
    }

    public void setPhoneConfirmationTime(Object phoneConfirmationTime) {
        this.phoneConfirmationTime = phoneConfirmationTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmedEmail() {
        return confirmedEmail;
    }

    public void setConfirmedEmail(String confirmedEmail) {
        this.confirmedEmail = confirmedEmail;
    }

    public Object getEmailConfirmationTime() {
        return emailConfirmationTime;
    }

    public void setEmailConfirmationTime(Object emailConfirmationTime) {
        this.emailConfirmationTime = emailConfirmationTime;
    }

    public Object getCityId() {
        return cityId;
    }

    public void setCityId(Object cityId) {
        this.cityId = cityId;
    }

    public Object getPromo() {
        return promo;
    }

    public void setPromo(Object promo) {
        this.promo = promo;
    }

}
