package com.px.testlib;

import android.content.res.Configuration;
import android.util.Log;

import com.px.annotation.AppLogic;
import com.px.api.BaseAppLogic;

@AppLogic
public class TestApplication extends BaseAppLogic {
    private static final String TAG = "TestApplication";
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG,"test for application");
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}

