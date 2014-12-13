package com.hawa.scrap.dependencyinjection;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.google.inject.Injector;
import com.google.inject.Module;

import java.util.ArrayList;
import java.util.List;


public abstract class DependencyInjectionFragment extends Fragment {

    private Injector mInjector;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Create the activity graph by .plus-ing our modules onto the application graph.
        DependencyInjectionActivity activity = (DependencyInjectionActivity) getActivity();
        mInjector = activity.getInjector().createChildInjector(getModules());

        mInjector.injectMembers(this);
    }

    protected List<Module> getModules() {
        return new ArrayList<Module>();
    }

    @Override
    public void onDestroy() {
        mInjector = null;
        super.onDestroy();
    }

}
