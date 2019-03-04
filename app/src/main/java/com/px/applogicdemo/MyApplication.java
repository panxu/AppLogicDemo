package com.px.applogicdemo;

import android.app.Application;
import android.content.res.Configuration;
import com.px.api.AppLogicInject;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppLogicInject.inject(this);
        AppLogicInject.onCreate();
        //其他的初始化操作
        AppLogicInject.onCreateDelay();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        AppLogicInject.onTerminate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        AppLogicInject.onLowMemory();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        AppLogicInject.onTrimMemory(level);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        AppLogicInject.onConfigurationChanged(newConfig);
    }
}
