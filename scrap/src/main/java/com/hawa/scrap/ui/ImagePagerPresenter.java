package com.hawa.scrap.ui;

import android.support.v4.view.PagerAdapter;

import com.hawa.scrap.event.VolumePressEvent;

public interface ImagePagerPresenter {
    void handleVolumePress(VolumePressEvent event);
    PagerAdapter getAdaptor();
}
