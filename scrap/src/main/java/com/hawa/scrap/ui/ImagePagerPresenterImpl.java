package com.hawa.scrap.ui;

import android.content.Context;
import android.support.v4.view.PagerAdapter;

import com.hawa.scrap.dependencyinjection.ForActivity;
import com.hawa.scrap.domain.PostsService;
import com.hawa.scrap.event.VolumePressEvent;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

public class ImagePagerPresenterImpl implements ImagePagerPresenter {

    private ImagePagerView mView;
    private PostsService mPostsService;
    private Context mContext;

    @Inject
    public ImagePagerPresenterImpl(ImagePagerView view, PostsService postsService, @ForActivity Context context) {
        mView = view;
        mPostsService = postsService;
        mContext = context;
    }

    @Subscribe
    @Override
    public void handleVolumePress(VolumePressEvent event) {
        int currentIndex = mView.getIndex();

        if (event.getVolume() == VolumePressEvent.Volume.Up) {
            if (currentIndex > 0) {
                mView.setIndex(currentIndex - 1);
            }

        } else if (event.getVolume() == VolumePressEvent.Volume.Down) {
            mView.setIndex(currentIndex + 1);
        }
    }

    @Override
    public PagerAdapter getAdaptor() {
        return new InfiniteImagePagerAdapter(mContext, mPostsService);
    }

}
