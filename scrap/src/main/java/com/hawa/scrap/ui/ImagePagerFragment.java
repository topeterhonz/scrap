package com.hawa.scrap.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.inject.Module;
import com.hawa.scrap.R;
import com.hawa.scrap.dependencyinjection.DependencyInjectionFragment;
import com.hawa.scrap.module.ImagePagerModule;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;



public class ImagePagerFragment extends DependencyInjectionFragment implements ImagePagerView {

    public static final String TAG = "ImagePagerFragment";

    @Inject
    ImagePagerPresenter mPresenter;

    private ViewPager mPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_image_pager, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        mPager = (ViewPager) getView().findViewById(R.id.view_pager);
        mPager.setOffscreenPageLimit(2);
        mPager.setAdapter(mPresenter.getAdaptor());
    }

    @Override
    public int getIndex() {
        return mPager.getCurrentItem();
    }

    @Override
    public void setIndex(int index) {
        mPager.setCurrentItem(index, true);
    }

    @Override
    protected List<Module> getModules() {
        return Arrays.<Module>asList(new ImagePagerModule(this));
    }
}
