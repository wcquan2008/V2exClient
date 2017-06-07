package com.github.wcquan.library.app;

import android.os.Environment;

import java.io.File;

/**
 * Created by WCQUAN on 2017-06-01.
 */

public class Constants {

    public static final String VSERION_NAME = "1.0";

    //================= PATH ====================

    public static final String PATH_DATA = BaseApplication.getInstance().getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/NetCache";

    public static final String PATH_SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "codeest" + File.separator + "GeekNews";

    //================= PREFERENCE ====================

    public static final String SP_NIGHT_MODE = "night_mode";

    public static final String SP_NO_IMAGE = "no_image";

    public static final String SP_AUTO_CACHE = "auto_cache";

    //================= INTENT ====================
    public static final String IT_V2EX_TYPE = "v2ex_type";

    public static final String IT_V2EX_TOPIC_ID = "v2ex_id";
}

