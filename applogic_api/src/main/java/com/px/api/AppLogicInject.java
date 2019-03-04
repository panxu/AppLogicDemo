package com.px.api;

import android.app.Application;
import android.content.res.Configuration;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;

public class AppLogicInject {

    private static ArrayList<AppLogicInfo> appLogicInfos;
    private static Application mApplication;
    private static ArrayList<BaseAppLogic> appLogicList = new ArrayList<>();

    public static void inject(Application application) {
        mApplication = application;
        String className = "com.px.applogicdem.Test$$AppLogic";
        try {
            Class clasz = Class.forName(className);
            Object object = clasz.newInstance();
            Field field = object.getClass().getField("testArrayList");
            appLogicInfos = (ArrayList<AppLogicInfo>) field.get(object);
            Collections.sort(appLogicInfos, new AppLogicInfo());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void onCreate() {
        if(appLogicInfos == null){
            return;
        }
        for (AppLogicInfo appLogicInfo : appLogicInfos) {
            if (appLogicInfo.isDelay()) {
                continue;
            }
            createAppLogic(appLogicInfo);
        }
    }

    public static void onCreateDelay() {
        if(appLogicInfos == null){
            return;
        }
        for (AppLogicInfo appLogicInfo : appLogicInfos) {
            if (appLogicInfo.isDelay()) {
                createAppLogic(appLogicInfo);
            }
        }
    }

    private static void createAppLogic(AppLogicInfo appLogicInfo) {
        try {
            Class<?> clazz = Class.forName(appLogicInfo.getClassName());
            BaseAppLogic appLogic = (BaseAppLogic) clazz.newInstance();
            appLogic.setApplication(mApplication);
            appLogic.onCreate();
            appLogicList.add(appLogic);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public static void onTerminate() {
        for (BaseAppLogic appLogic : appLogicList) {
            appLogic.onTerminate();
        }
    }

    public static void onLowMemory() {
        for (BaseAppLogic appLogic : appLogicList) {
            appLogic.onLowMemory();
        }
    }

    public static void onTrimMemory(int level) {
        for (BaseAppLogic appLogic : appLogicList) {
            appLogic.onTrimMemory(level);
        }
    }

    public static void onConfigurationChanged(Configuration newConfig) {
        for (BaseAppLogic appLogic : appLogicList) {
            appLogic.onConfigurationChanged(newConfig);
        }
    }

}
