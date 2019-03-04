package com.px.api;

import android.app.Application;
import android.content.res.Configuration;

public abstract class BaseAppLogic {

    protected Application mApplication;

    public BaseAppLogic(){
    }

    public void setApplication(Application application){
        this.mApplication = application;
    }

    public void onCreate(){}

    public void onTerminate(){}

    public void onLowMemory(){}

    public void onTrimMemory(int level){}

    public void onConfigurationChanged(Configuration newConfig){}
}
