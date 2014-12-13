package com.hawa.scrap.domain;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.hawa.scrap.framework.ErrorCode;
import com.hawa.scrap.framework.ResultCallback;
import com.hawa.scrap.framework.ResultCallbackList;
import com.hawa.scrap.model.Post;
import com.hawa.scrap.model.PostsResponse;
import com.hawa.scrap.operation.TumblrPostsOperation;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.ConversionException;

@Singleton
public class PostsService {
    private static final String TYPE = "photo";

    private List<Post> mCache;
    private ResultCallbackList<List<Post>, ResultCode> mCallbacks = new ResultCallbackList<List<Post>, ResultCode>();
    private int mTotalPosts;


    private ConfigurationsService mConfigurationsService;
    private TumblrPostsOperation mTumblrPostsOperation;

    public enum ResultCode {
        Success,
        EndReached,
    }

    @Inject
    public PostsService(ConfigurationsService configurationsService, TumblrPostsOperation tumblrPostsOperation) {
        mConfigurationsService = configurationsService;
        mTumblrPostsOperation = tumblrPostsOperation;
    }

    public void getPostsForNextPage(final ResultCallback<List<Post>,ResultCode> callback) {
        callback.onProcessing();
        mCallbacks.add(callback);

        // If it's the second subscriber, we are already running.
        if (mCallbacks.size() > 1) {
            return;
        }

        if (mCache == null) {
            mCache = new ArrayList<Post>();
        }

        mTumblrPostsOperation.getPosts(
                mConfigurationsService.getSiteId(),
                TYPE, mCache.size(),
                mConfigurationsService.getPostPageSize(),
                new Callback<PostsResponse>() {

            @Override
            public void success(PostsResponse postsResponse, Response response) {
                mCache.addAll(postsResponse.getPosts());
                mTotalPosts = postsResponse.getTotalPosts();
                mCallbacks.onResult(mCache, mCache.size() < mTotalPosts ? ResultCode.Success : ResultCode.EndReached);
            }

            @Override
            public void failure(RetrofitError error) {
                ErrorCode errorCode;
                if (error.isNetworkError()) {
                    errorCode = ErrorCode.NetworkError;
                } else if (error.getCause() instanceof ConversionException) {
                    errorCode = ErrorCode.DataError;
                } else if (error.getCause() == null) {
                    errorCode = ErrorCode.ServerError;
                } else {
                    errorCode = ErrorCode.GeneralError;
                }
                mCallbacks.onError(errorCode);
            }
        });

    }

    public List<Post> getPostsCached() {
        return mCache;
    }

    public int getTotalPosts() {
        return mTotalPosts;
    }

}
