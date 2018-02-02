package com.example.ggxiaozhi.tinker.tinker;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.multidex.MultiDex;

import com.tencent.tinker.anno.DefaultLifeCycle;
import com.tencent.tinker.loader.app.ApplicationLike;
import com.tencent.tinker.loader.app.DefaultApplicationLike;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * 工程名 ： Tinker
 * 包名   ： com.example.ggxiaozhi.tinker.tinker
 * 作者名 ： 志先生_
 * 日期   ： 2018/01
 * 功能   ：ApplicationLike为Tinker生成Context对象 官方建议 而不是继承我们自己的Application
 * 作用   ：使用这个ApplicationLike这个类作为Application的委托代理是因为，Tinker需要监听Application
 * 的生命周期并针对不同的生命周期来做相应的初始化与处理，这样就减少使用者需要自己处理。
 */
@DefaultLifeCycle(application = ".MyTinkerApplication" ,
        flags = ShareConstants.TINKER_ENABLE_ALL,
        loadVerifyFlag = false)//都是官方要求这么写的
public class CustomTinkerLike extends DefaultApplicationLike {
    public CustomTinkerLike(Application application,
                            int tinkerFlags,
                            boolean tinkerLoadVerifyFlag,
                            long applicationStartElapsedTime,
                            long applicationStartMillisTime,
                            Intent tinkerResultIntent) {
        super(application,
                tinkerFlags,
                tinkerLoadVerifyFlag,
                applicationStartElapsedTime,
                applicationStartMillisTime,
                tinkerResultIntent);
    }

    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);

        // 其原理是分包架构，所以在加载初要加载其余的分包
        MultiDex.install(base);
        /*下面才是与Tinker初始化相关==========================================================*/
        TinkerManager.installedTinker(this);
    }
}
