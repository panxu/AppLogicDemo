package com.px.compiler;

public class AppLogicInfo {
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
}
