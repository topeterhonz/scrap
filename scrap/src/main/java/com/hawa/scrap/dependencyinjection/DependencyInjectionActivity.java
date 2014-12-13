package com.hawa.scrap.dependencyinjection;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import java.util.List;

import dagger.ObjectGraph;

/**
 * Base activity which sets up a per-activity object graph and performs injection.
 */
public abstract class DependencyInjectionActivity extends FragmentActivity {
    private ObjectGraph mObjectGraph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create the activity graph by .plus-ing our modules onto the application graph.
        DependencyInjectionApplication application = (DependencyInjectionApplication) getApplication();
        mObjectGraph = application.getObjectGraph().plus(getModules().toArray());

        // Inject ourselves so subclasses will have dependencies fulfilled when this method returns.
        mObjectGraph.inject(this);
    }

    @Override
    protected void onDestroy() {
        // Eagerly clear the reference to the activity graph to allow it to be garbage collected as
        // soon as possible.
        mObjectGraph = null;

        super.onDestroy();
    }

    protected abstract List<Object> getModules();

    /**
     * Inject the supplied {@code object} using the activity-specific graph.
     */
    public void inject(Object object) {
        mObjectGraph.inject(object);
    }

    public ObjectGraph getObjectGraph() {
        return mObjectGraph;
    }
}
