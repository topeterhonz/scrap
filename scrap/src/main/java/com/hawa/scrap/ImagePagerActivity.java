package com.hawa.scrap;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;

import com.google.inject.Inject;
import com.hawa.scrap.ioc.InjectionActivity;

public class ImagePagerActivity extends InjectionActivity {

    private ViewPager mPager;
    private ProgressDialog mProgressDialog;

    @Inject
    private TumblrImageProvider mImageProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE);

        setContentView(R.layout.activity_image_pager);

        mImageProvider.initialise("androidniceties.tumblr.com");
        mPager = (ViewPager) findViewById(R.id.view_pager);
        mPager.setOffscreenPageLimit(2);
        mPager.setAdapter(new InfiniteImagePagerAdapter(ImagePagerActivity.this, mImageProvider));
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int keyCode = event.getKeyCode();
        int action = event.getAction();

        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP || keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            final int currentIndex = mPager.getCurrentItem();
            if (keyCode == KeyEvent.KEYCODE_VOLUME_UP && action == KeyEvent.ACTION_DOWN) {
                if (currentIndex > 0) {
                    mPager.setCurrentItem(currentIndex - 1, true);
                }
            } else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN && action == KeyEvent.ACTION_DOWN) {
                mPager.setCurrentItem(currentIndex + 1, true);
            }
            return true;
        } else {
            return super.dispatchKeyEvent(event);
        }
    }

}
