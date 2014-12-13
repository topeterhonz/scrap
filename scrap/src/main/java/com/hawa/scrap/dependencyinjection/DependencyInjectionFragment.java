package com.hawa.scrap.dependencyinjection;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.util.List;

import dagger.Module;
import dagger.ObjectGraph;

public abstract class DependencyInjectionFragment extends Fragment {

    private ObjectGraph mObjectGraph;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Create the activity graph by .plus-ing our modules onto the application graph.
        DependencyInjectionActivity activity = (DependencyInjectionActivity) getActivity();
        mObjectGraph = activity.getObjectGraph().plus(getModules().toArray());

        mObjectGraph.inject(this);
    }

    protected abstract List<Object> getModules();

    @Override
    public void onDestroy() {
        mObjectGraph = null;
        super.onDestroy();
    }

}
