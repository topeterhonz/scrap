package com.hawa.scrap.model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Post {

    private String blog_name;
    private Integer id;
    private String post_url;
    private String slug;
    private String type;
    private String date;
    private Integer timestamp;
    private String state;
    private String format;
    private String reblog_key;
    private List<String> tags = new ArrayList<String>();
    private String short_url;
    private Integer note_count;
    private String caption;
    private String image_permalink;
    private List<Photo> photos = new ArrayList<Photo>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getBlog_name() {
        return blog_name;
    }

    public void setBlog_name(String blog_name) {
        this.blog_name = blog_name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPost_url() {
        return post_url;
    }

    public void setPost_url(String post_url) {
        this.post_url = post_url;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getReblog_key() {
        return reblog_key;
    }

    public void setReblog_key(String reblog_key) {
        this.reblog_key = reblog_key;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getShort_url() {
        return short_url;
    }

    public void setShort_url(String short_url) {
        this.short_url = short_url;
    }

    public Integer getNote_count() {
        return note_count;
    }

    public void setNote_count(Integer note_count) {
        this.note_count = note_count;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getImage_permalink() {
        return image_permalink;
    }

    public void setImage_permalink(String image_permalink) {
        this.image_permalink = image_permalink;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
