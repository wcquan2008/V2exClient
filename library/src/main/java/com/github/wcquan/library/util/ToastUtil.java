package com.github.wcquan.library.util;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;

import es.dmoral.toasty.Toasty;


/**
 * Created by WCQUAN on 2017-06-01.
 */

public class ToastUtil {

    public static void showSuccess(Context context, String msg) {
        Toasty.success(context, msg).show();
    }

    public static void showFail(Context context, String msg) {
        Toasty.error(context, msg).show();
    }

    //Snackbar样式
    public static void show(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show();
    }

    public static void showShort(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
    }
}
