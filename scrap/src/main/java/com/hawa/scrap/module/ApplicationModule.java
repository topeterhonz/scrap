package com.hawa.scrap.module;


import android.content.Context;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.hawa.scrap.Application;
import com.hawa.scrap.dependencyinjection.ForApplication;
import com.squareup.otto.Bus;

import javax.inject.Singleton;


public class ApplicationModule extends AbstractModule {
    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ForApplication
    Context provideApplicationContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    Bus provideBus() {
        return new Bus();
    }

    @Override
    protected void configure() {

    }
}
