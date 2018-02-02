package com.example.ggxiaozhi.tinker.tinker;

import android.content.Context;

import com.example.ggxiaozhi.tinker.service.SampleResultService;
import com.tencent.tinker.lib.listener.DefaultPatchListener;
import com.tencent.tinker.lib.patch.AbstractPatch;
import com.tencent.tinker.lib.patch.UpgradePatch;
import com.tencent.tinker.lib.reporter.DefaultLoadReporter;
import com.tencent.tinker.lib.reporter.DefaultPatchReporter;
import com.tencent.tinker.lib.reporter.LoadReporter;
import com.tencent.tinker.lib.reporter.PatchReporter;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.loader.app.ApplicationLike;

/**
 * 工程名 ： Tinker
 * 包名   ： com.example.ggxiaozhi.tinker.tinker
 * 作者名 ： 志先生_
 * 日期   ： 2018/01
 * 功能   ：Tinker管理类
 */

public class TinkerManager {

    private static boolean isInstalled = false;//是否已经初始化标志位
    private static ApplicationLike mApplicationLike;
    private static DefaultPatchListener mPatchListener;

    /**
     * 完成Tinker初始化
     *
     * @param applicationLike
     */
    public static void installedTinker(ApplicationLike applicationLike) {
        mApplicationLike = applicationLike;
        if (isInstalled) {
            return;
        }
//        TinkerInstaller.install(mApplicationLike);
        mPatchListener = new DefaultPatchListener(getApplicationContext());
        //这两个是监听patch文件安装的日志上报结果 也就是补丁文件安装监听
        LoadReporter loadReporter = new DefaultLoadReporter(getApplicationContext());//一些在加载补丁文件时的回调
        PatchReporter patchReporter = new DefaultPatchReporter(getApplicationContext());//补丁文件在合成时一些事件的回调

        AbstractPatch abstractPatch = new UpgradePatch();//决定patch文件安装策略  不会去修改与自定义
        TinkerInstaller.install(mApplicationLike,
                loadReporter,
                patchReporter,
                mPatchListener,
                CustomResultService.class,//我们自定义的
                abstractPatch);
        isInstalled = true;
    }

    /**
     * 完成patch文件的加载
     *
     * @param path 补丁文件路径
     * @param md5Value 与补丁文件一起返回的检验MD5
     */
    public static void loadPatch(String path,String md5Value) {
        if (Tinker.isTinkerInstalled()) {//是否已经安装过
            TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(), path);
        }
    }

    /**
     * 利用Tinker代理Application 获取应用全局的上下文
     *
     * @return 全局的上下文
     */
    private static Context getApplicationContext() {
        if (mApplicationLike != null)
            return mApplicationLike.getApplication().getApplicationContext();
        return null;
    }
}
