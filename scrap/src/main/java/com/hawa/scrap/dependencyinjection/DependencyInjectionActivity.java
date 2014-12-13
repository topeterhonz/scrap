package com.hawa.scrap.dependencyinjection;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.inject.Injector;
import com.google.inject.Module;

import java.util.List;

/**
 * Base activity which sets up a per-activity object graph and performs injection.
 */
public abstract class DependencyInjectionActivity extends FragmentActivity {

    private Injector mInjector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Create the activity graph by .plus-ing our modules onto the application graph.
        DependencyInjectionApplication application = (DependencyInjectionApplication) getApplication();
        mInjector = application.getInjector().createChildInjector(getModules());

        // Inject ourselves so subclasses will have dependencies fulfilled when this method returns.
        mInjector.injectMembers(this);
    }

    @Override
    protected void onDestroy() {
        // Eagerly clear the reference to the activity graph to allow it to be garbage collected as
        // soon as possible.
        mInjector = null;

        super.onDestroy();
    }

    protected abstract List<Module> getModules();

    /**
     * Inject the supplied {@code object} using the activity-specific graph.
     */
    public void inject(Object object) {
        mInjector.injectMembers(object);
    }

    public Injector getInjector() {
        return mInjector;
    }
}
