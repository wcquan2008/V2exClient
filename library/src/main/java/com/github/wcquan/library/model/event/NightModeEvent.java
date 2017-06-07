package com.github.wcquan.library.model.event;

/**
 * Created by WCQUAN on 2017-06-02.
 */

public class NightModeEvent {
    private boolean isNightMode;

    public void setNightMode(boolean nightMode) {
        isNightMode = nightMode;
    }

    public boolean getNightMode() {
        return isNightMode;
    }
}
