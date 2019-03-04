package com.px.api;

import java.util.Comparator;

public class AppLogicInfo implements Comparator<AppLogicInfo> {
    private String className;
    private boolean delay;
    private int priority;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public boolean isDelay() {
        return delay;
    }

    public void setDelay(boolean delay) {
        this.delay = delay;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }


    @Override
    public int compare(AppLogicInfo appLogicInfo, AppLogicInfo t1) {
        if(appLogicInfo.priority > t1.priority){
            return 1;
        }else if(appLogicInfo.priority < t1.priority){
            return -1;
        }
        return 0;
    }
}
