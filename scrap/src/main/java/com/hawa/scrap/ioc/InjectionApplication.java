package com.hawa.scrap.ioc;

import android.app.Application;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.hawa.scrap.module.ServiceModule;

public class InjectionApplication extends Application {

    private Injector mInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        mInjector = Guice.createInjector(new ServiceModule());
    }

    public Injector getInjector() {
        return mInjector;
    }
}
