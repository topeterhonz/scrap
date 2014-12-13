package com.hawa.scrap.ui;

import android.content.Context;

import com.hawa.scrap.dependencyinjection.ForActivity;
import com.hawa.scrap.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        injects = {
                MainActivity.class,
        },
        addsTo = ApplicationModule.class,
        library = true
)
public class MainModule {

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

}