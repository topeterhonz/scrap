package com.hawa.scrap.dependencyinjection;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

import java.util.List;

public abstract class DependencyInjectionApplication extends android.app.Application {

    private Injector mInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        mInjector = Guice.createInjector(getModules());
        mInjector.injectMembers(this);
    }

    protected abstract List<Module> getModules();

    public Injector getInjector() {
        return mInjector;
    }

    public void inject(Object object) {
        mInjector.injectMembers(object);
    }

}
