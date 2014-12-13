package com.hawa.scrap.dependencyinjection;

import java.util.List;

import dagger.ObjectGraph;

public abstract class DependencyInjectionApplication extends android.app.Application {
    private ObjectGraph mObjectGraph;

    @Override
    public void onCreate() {
        super.onCreate();

        mObjectGraph = ObjectGraph.create(getModules().toArray());
        mObjectGraph.inject(this);
    }

    protected abstract List<Object> getModules() ;

    public ObjectGraph getObjectGraph() {
        return mObjectGraph;
    }

    public ObjectGraph createScopedGraph(Object... modules) {
        return mObjectGraph.plus(modules);
    }
}
