package com.hawa.scrap.ui;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

import com.hawa.scrap.R;
import com.hawa.scrap.dependencyinjection.DependencyInjectionActivity;
import com.hawa.scrap.event.VolumePressEvent;
import com.hawa.scrap.module.MainModule;
import com.squareup.otto.Bus;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends DependencyInjectionActivity {

    @Inject
    Bus mBus;

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

        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            ImagePagerFragment imagePagerFragment = new ImagePagerFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.main_container, imagePagerFragment, ImagePagerFragment.TAG)
                    .commit();
        }
    }

    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new MainModule(this));
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int keyCode = event.getKeyCode();
        int action = event.getAction();

        if (keyCode == KeyEvent.KEYCODE_VOLUME_UP || keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
            if (keyCode == KeyEvent.KEYCODE_VOLUME_UP && action == KeyEvent.ACTION_DOWN) {
                mBus.post(new VolumePressEvent(VolumePressEvent.Volume.Up));

            } else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN && action == KeyEvent.ACTION_DOWN) {
                mBus.post(new VolumePressEvent(VolumePressEvent.Volume.Down));
            }
            return true;
        } else {
            return super.dispatchKeyEvent(event);
        }
    }

}
