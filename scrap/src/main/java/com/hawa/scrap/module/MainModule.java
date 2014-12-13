package com.hawa.scrap.module;

import android.app.Activity;
import android.content.Context;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.hawa.scrap.dependencyinjection.ForActivity;
import com.hawa.scrap.ui.MainActivity;

import javax.inject.Singleton;


public class MainModule extends AbstractModule {

    private final MainActivity mMainActivity;


    public MainModule(MainActivity mainActivity) {
        mMainActivity = mainActivity;
    }

    /**
     * Allow the activity context to be injected but require that it be annotated with {@link ForActivity @ForActivity} to explicitly differentiate it
     * from application context.
     */
    @Provides
    @Singleton
    @ForActivity
    Context provideActivityContext() {
        return mMainActivity;
    }

    @Provides
    @Singleton
    Activity provideActivity() {
        return mMainActivity;
    }


    @Override
    protected void configure() {

    }

}