package com.hawa.scrap;

import com.hawa.scrap.dependencyinjection.DependencyInjectionApplication;
import com.hawa.scrap.module.ApplicationModule;

import java.util.List;
import java.util.Arrays;

public class Application extends DependencyInjectionApplication {

    @Override
    protected List<Object> getModules() {
        return Arrays.<Object>asList(new ApplicationModule(this));
    }
}
