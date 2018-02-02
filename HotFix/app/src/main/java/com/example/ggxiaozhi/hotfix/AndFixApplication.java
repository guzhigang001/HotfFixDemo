package com.example.ggxiaozhi.hotfix;

import android.app.Application;

/**
 * 工程名 ： HotFix
 * 包名   ： com.example.ggxiaozhi.hotfix
 * 作者名 ： 志先生_
 * 日期   ： 2018/01
 * 功能   ：初始化AndFix
 */

public class AndFixApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initAndFix();
    }

    private void initAndFix() {
        AndFixPatchManager.getInstance().initPatch(this);
    }
}
