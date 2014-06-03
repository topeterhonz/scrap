package com.hawa.scrap;

import com.google.inject.Inject;
import com.hawa.scrap.model.Post;
import com.hawa.scrap.model.PostsResponse;
import com.hawa.scrap.network.TumblrService;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class PostCache {

    private static final int PAGE_SIZE = 20;
    private static final String TYPE = "photo";
    private TumblrService mService;
    private List<Post> mList = null;
    private String mSite;
    private int mTotalSize;
    private List<CompleteHandler> mCompleteHandlers = new ArrayList<CompleteHandler>();

    @Inject
    public PostCache(TumblrService service) {
        mService = service;
    }

    public interface CompleteHandler {
        public void onSuccess();
        public void onFailure();
    }


    public void initialise(boolean refresh, String site) {
        if (refresh || site != mSite) {
            mSite = site;
            mTotalSize = 0;
            mList = new ArrayList<Post>();
        }
    }

    public void getNextPosts(final CompleteHandler completeHandler) {
        getPosts(completeHandler, mList.size());
    }

    private void onSuccess() {
        for (CompleteHandler completeHandler : mCompleteHandlers) {
            completeHandler.onSuccess();
        }
        mCompleteHandlers.clear();
    }

    private void onFailure() {
        for (CompleteHandler completeHandler : mCompleteHandlers) {
            completeHandler.onFailure();
        }
        mCompleteHandlers.clear();
    }

    private void getPosts(final CompleteHandler completeHandler, int offset) {

        mCompleteHandlers.add(completeHandler);

        // Only schedule the request once. All other request will depend on the same response
        if (mCompleteHandlers.size() == 1) {
            mService.getPosts(mSite, TYPE, offset, PAGE_SIZE, new Callback<PostsResponse>() {
                @Override
                public void success(PostsResponse postsResponse, Response response) {
                    mList.addAll(postsResponse.getPosts());
                    mTotalSize = postsResponse.getTotalPosts();
                    onSuccess();
                }

                @Override
                public void failure(RetrofitError error) {
                    onFailure();
                }
            });
        }
    }

    public List<Post> getCachedResults() {
        return mList;
    }

    public int getCurrentSize() {
        return mList.size();
    }

    public int getTotalSize() {
        return mTotalSize;
    }
}
