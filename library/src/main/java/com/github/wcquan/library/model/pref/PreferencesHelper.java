package com.github.wcquan.library.model.pref;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

import com.github.wcquan.library.app.BaseApplication;
import com.github.wcquan.library.app.Constants;


/**
 * Created by WCQUAN on 2017-06-01.
 */

public class PreferencesHelper implements IPreferencesHelper {

    private static final boolean DEFAULT_NIGHT_MODE = false;
    private static final boolean DEFAULT_NO_IMAGE = false;
    private static final boolean DEFAULT_AUTO_SAVE = true;

    private final SharedPreferences mSPrefs;

    public PreferencesHelper(SharedPreferences sharedPreferences) {
        this.mSPrefs = sharedPreferences;
    }

    @Override
    public boolean getNightModeState() {
        return mSPrefs.getBoolean(Constants.SP_NIGHT_MODE, DEFAULT_NIGHT_MODE);
    }

    @Override
    public void setNightModeState(boolean state) {
        mSPrefs.edit().putBoolean(Constants.SP_NIGHT_MODE, state).apply();
    }

    @Override
    public boolean getNoImageState() {
        return mSPrefs.getBoolean(Constants.SP_NO_IMAGE, DEFAULT_NO_IMAGE);
    }

    @Override
    public void setNoImageState(boolean state) {
        mSPrefs.edit().putBoolean(Constants.SP_NO_IMAGE, state).apply();
    }

    @Override
    public boolean getAutoCacheState() {
        return mSPrefs.getBoolean(Constants.SP_AUTO_CACHE, DEFAULT_AUTO_SAVE);
    }

    @Override
    public void setAutoCacheState(boolean state) {
        mSPrefs.edit().putBoolean(Constants.SP_AUTO_CACHE, state).apply();
    }
}
