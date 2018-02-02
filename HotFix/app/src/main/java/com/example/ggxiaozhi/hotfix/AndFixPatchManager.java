package com.example.ggxiaozhi.hotfix;

import android.content.Context;

import com.alipay.euler.andfix.patch.PatchManager;

import java.io.IOException;

/**
 * 工程名 ： ClassLoaderTest
 * 包名   ： com.example.ggxiaozhi.classloadertest.andfix
 * 作者名 ： 志先生_
 * 日期   ： 2018/01
 * 功能   ：管理AndFix
 */

public class AndFixPatchManager {

    private static AndFixPatchManager mInstance;

    private static PatchManager mPatchManager;

    public static AndFixPatchManager getInstance() {
        if (mInstance == null) {
            synchronized (AndFixPatchManager.class) {
                if (mInstance == null) {
                    mInstance = new AndFixPatchManager();
                }
            }
        }
        return mInstance;
    }

    /**
     * 初始化AndFix
     *
     * @param context 上下文
     */
    public void initPatch(Context context) {
        //初始化
        mPatchManager = new PatchManager(context);
        mPatchManager.init(Utils.getVersionName(context));
        //加载patch
        mPatchManager.loadPatch();
    }

    /**
     * 加载我们的Patch文件
     * @param path .patch文件  路径
     */
    public void addPatch(String path) {
        try {
            if (mInstance!=null){
                mPatchManager.addPatch(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
