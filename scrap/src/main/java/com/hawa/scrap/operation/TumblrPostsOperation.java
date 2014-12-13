package com.hawa.scrap.operation;

import com.hawa.scrap.model.Blog;
import com.hawa.scrap.model.Post;
import com.hawa.scrap.model.PostsResponse;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

public interface TumblrPostsOperation {
    @GET("/v2/blog/{base-hostname}/info")
    void getBlog(Callback<Blog> callback);

    @GET("/v2/blog/{base-hostname}/posts/{type}")
    void getPosts(@Path("base-hostname") String site,
                  @Path("type") String type,
                  @Query("offset") int offset,
                  @Query("limit") int limit,
                  Callback<PostsResponse> callback);

}
