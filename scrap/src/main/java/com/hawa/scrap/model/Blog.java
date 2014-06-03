package com.hawa.scrap.model;

import java.util.HashMap;
import java.util.Map;


public class Blog {

    private String title;
    private String name;
    private int posts;
    private String url;
    private int updated;
    private String description;
    private boolean ask;
    private String ask_page_title;
    private boolean ask_anon;
    private boolean is_nsfw;
    private boolean share_likes;
    private int likes;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPosts() {
        return posts;
    }

    public void setPosts(int posts) {
        this.posts = posts;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getUpdated() {
        return updated;
    }

    public void setUpdated(int updated) {
        this.updated = updated;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAsk() {
        return ask;
    }

    public void setAsk(boolean ask) {
        this.ask = ask;
    }

    public String getAsk_page_title() {
        return ask_page_title;
    }

    public void setAsk_page_title(String ask_page_title) {
        this.ask_page_title = ask_page_title;
    }

    public boolean isAsk_anon() {
        return ask_anon;
    }

    public void setAsk_anon(boolean ask_anon) {
        this.ask_anon = ask_anon;
    }

    public boolean isIs_nsfw() {
        return is_nsfw;
    }

    public void setIs_nsfw(boolean is_nsfw) {
        this.is_nsfw = is_nsfw;
    }

    public boolean isShare_likes() {
        return share_likes;
    }

    public void setShare_likes(boolean share_likes) {
        this.share_likes = share_likes;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}