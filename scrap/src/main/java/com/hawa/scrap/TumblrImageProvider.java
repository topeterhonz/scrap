package com.hawa.scrap;

import com.google.inject.Inject;

public class TumblrImageProvider {

    public interface LoadCompleteListener {
        public void onSuccess();
        public void onFailure();
    }

    private PostCache mPostCache;


    @Inject
    public TumblrImageProvider(PostCache postCache) {
        mPostCache = postCache;
    }

    public void loadNext(final LoadCompleteListener completeListener) {
        mPostCache.getNextPosts(new PostCache.CompleteHandler() {
            @Override
            public void onSuccess() {
                completeListener.onSuccess();
            }

            @Override
            public void onFailure() {
                completeListener.onFailure();
            }
        });
    }

    public void initialise(String site) {
        mPostCache.initialise(false, site);
    }

    public String provideImageUrl(int index) {
        return mPostCache.getCachedResults().get(index).getPhotos().get(0).getOriginal_size().getUrl();
    }

    public String provideThumbImageUrl(int index) {
        return mPostCache.getCachedResults().get(index).getPhotos().get(0).getAlt_sizes().get(0).getUrl();
    }

    public boolean isAvailable(int index) {
        return mPostCache.getCurrentSize() > index;
    }

    public int getTotalSize() {
        return mPostCache.getTotalSize();
    }
}
