package com.example.ggxiaozhi.tinker.tinker;

import android.content.Context;

import com.example.ggxiaozhi.tinker.Utils;
import com.tencent.tinker.lib.listener.DefaultPatchListener;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * 工程名 ： Tinker
 * 包名   ： com.example.ggxiaozhi.tinker.tinker
 * 作者名 ： 志先生_
 * 日期   ： 2018/01
 * 功能   ：1.校验patch文件是否合法(通常会进行一些MD5校验) 2. 启动Service去安装patch文件（这个一般我们不会修改）
 */

    public class CustomPatchListener extends DefaultPatchListener {
    private String currentMD5;

    public void setCurrentMD5(String md5) {
        this.currentMD5 = md5;
    }

    public CustomPatchListener(Context context) {
        super(context);
    }

    @Override
    protected int patchCheck(String path) {
        if (!Utils.isFileMD5Matched(path, currentMD5)) {//校验我们的补丁文件是否完整
            return ShareConstants.ERROR_PATCH_DISABLE;
        }
        return super.patchCheck(path);
    }
}
