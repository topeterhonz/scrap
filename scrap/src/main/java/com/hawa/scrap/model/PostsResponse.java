package com.hawa.scrap.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PostsResponse {

    private List<Post> posts;

    @SerializedName("total_posts")
    private int totalPosts;

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public int getTotalPosts() {
        return totalPosts;
    }

    public void setTotalPosts(int totalPosts) {
        this.totalPosts = totalPosts;
    }
}