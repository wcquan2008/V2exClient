package com.github.wcquan.library.model.pref;


/**
 * Created by WCQUAN on 2017-06-01.
 */

public interface IPreferencesHelper {

    boolean getNightModeState();

    void setNightModeState(boolean state);

    boolean getNoImageState();

    void setNoImageState(boolean state);

    boolean getAutoCacheState();

    void setAutoCacheState(boolean state);


}
