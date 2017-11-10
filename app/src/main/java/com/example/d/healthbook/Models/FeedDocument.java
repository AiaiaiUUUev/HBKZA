package com.example.d.healthbook.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by D on 15.06.2017.
 */

public class FeedDocument {

    @SerializedName("type")
    @Expose
    private Integer type;

    @SerializedName("user_id")
    @Expose
    private Object userId;
    @SerializedName("category_id")
    @Expose
    private Object categoryId;
    @SerializedName("channel_id")
    @Expose
    private Object channelId;
    @SerializedName("category_name")
    @Expose
    private Object categoryName;
    @SerializedName("channel_name")
    @Expose
    private Object channelName;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("preview")
    @Expose
    private String preview;
    @SerializedName("body")
    @Expose
    private Object body;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Object getUserId() {
        return userId;
    }

    public void setUserId(Object userId) {
        this.userId = userId;
    }

    public Object getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Object categoryId) {
        this.categoryId = categoryId;
    }

    public Object getChannelId() {
        return channelId;
    }

    public void setChannelId(Object channelId) {
        this.channelId = channelId;
    }

    public Object getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(Object categoryName) {
        this.categoryName = categoryName;
    }

    public Object getChannelName() {
        return channelName;
    }

    public void setChannelName(Object channelName) {
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

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public Object getLanguage() {
        return language;
    }

    public void setLanguage(Object language) {
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

    public Object getRate() {
        return rate;
    }

    public void setRate(Object rate) {
        this.rate = rate;
    }

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }

    public Object getImageSmall() {
        return imageSmall;
    }

    public void setImageSmall(Object imageSmall) {
        this.imageSmall = imageSmall;
    }

    @SerializedName("language")
    @Expose
    private Object language;
    @SerializedName("votes")
    @Expose
    private Object votes;
    @SerializedName("views")
    @Expose
    private Object views;
    @SerializedName("tags")
    @Expose
    private Object tags;
    @SerializedName("rate")
    @Expose
    private Object rate;
    @SerializedName("image")
    @Expose
    private Object image;
    @SerializedName("image_small")
    @Expose
    private Object imageSmall;






    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("created_time")
    @Expose
    private String createdTime;
    @SerializedName("updated_time")
    @Expose
    private String updatedTime;
    @SerializedName("entity_id")
    @Expose
    private String entityId;
    @SerializedName("entity_type")
    @Expose
    private Integer entityType;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("surname")
    @Expose
    private String surname;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public Integer getEntityType() {
        return entityType;
    }

    public void setEntityType(Integer entityType) {
        this.entityType = entityType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
