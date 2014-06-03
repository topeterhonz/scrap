package com.hawa.scrap.ioc;

import android.app.Activity;
import android.os.Bundle;

public class InjectionActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InjectionApplication application = (InjectionApplication)getApplication();
        application.getInjector().injectMembers(this);
    }
}
