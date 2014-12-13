package com.hawa.scrap.module;

import com.hawa.scrap.ui.ImagePagerFragment;
import com.hawa.scrap.ui.ImagePagerPresenter;
import com.hawa.scrap.ui.ImagePagerPresenterImpl;
import com.hawa.scrap.ui.ImagePagerView;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        injects = {
                ImagePagerFragment.class,
                ImagePagerPresenterImpl.class},
        addsTo = MainModule.class
)
public class ImagePagerModule {
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

}
