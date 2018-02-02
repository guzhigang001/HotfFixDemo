package com.example.ggxiaozhi.hotfix;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

/**
 * 工程名 ： ClassLoaderTest
 * 包名   ： com.example.ggxiaozhi.classloadertest.utils
 * 作者名 ： 志先生_
 * 日期   ： 2018/01
 * 功能   ：
 */

public class Utils {

    /**
     * 获取应用程序versionname
     *
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
        String versionName = "1.0.0";
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionName;
    }

    public static void printLog() {
        String error = null;
        Log.d("ggxiaozhi", error);
    }
}
