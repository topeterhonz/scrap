package com.hawa.scrap;

import com.google.inject.Module;
import com.hawa.scrap.dependencyinjection.DependencyInjectionApplication;
import com.hawa.scrap.module.ApplicationModule;
import com.hawa.scrap.module.ServiceModule;

import java.util.List;
import java.util.Arrays;

public class Application extends DependencyInjectionApplication {

    @Override
    protected List<Module> getModules() {
        return Arrays.<Module>asList(
                new ApplicationModule(this),
                new ServiceModule());
    }
}
