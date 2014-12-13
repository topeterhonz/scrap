package com.hawa.scrap.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.hawa.scrap.ui.ImagePagerFragment;
import com.hawa.scrap.ui.ImagePagerPresenter;
import com.hawa.scrap.ui.ImagePagerPresenterImpl;
import com.hawa.scrap.ui.ImagePagerView;
import com.squareup.otto.Bus;

import javax.inject.Singleton;


public class ImagePagerModule extends AbstractModule {
    private ImagePagerFragment mImagePagerFragment;

    public ImagePagerModule(ImagePagerFragment imagePagerFragment) {
        mImagePagerFragment = imagePagerFragment;
    }

    @Provides
    @Singleton
    ImagePagerView provideView() {
        return mImagePagerFragment;
    }

    @Provides
    @Singleton
    ImagePagerPresenter providesImagePagerPresenter (ImagePagerPresenterImpl impl, Bus bus) {
        bus.register(impl);
        return impl;
    }

    @Override
    protected void configure() {

    }
}
