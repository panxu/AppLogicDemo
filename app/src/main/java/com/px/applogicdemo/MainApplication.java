package com.px.applogicdemo;

import android.app.Application;

import com.px.api.BaseAppLogic;

import java.util.ArrayList;

public class MainApplication extends Application {
    private String[] appClazz = new String[]{"com.px.xxx.TestApplication"};
    private ArrayList<BaseAppLogic> applicationList = new ArrayList<>();
    @Override
    public void onCreate() {
        super.onCreate();
        initAppLogic();
    }

    private void initAppLogic(){
        for(String className : appClazz){
            try {
                Class<?> clazz = Class.forName(className);
                try {
                    BaseAppLogic appLogic = (BaseAppLogic) clazz.newInstance();
                    appLogic.onCreate();
                    applicationList.add(appLogic);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        for(BaseAppLogic appLogic:applicationList){
            appLogic.onTerminate();
        }
    }
}
