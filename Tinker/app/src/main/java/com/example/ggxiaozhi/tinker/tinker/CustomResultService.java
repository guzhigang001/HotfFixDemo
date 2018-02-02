package com.example.ggxiaozhi.tinker.tinker;

import com.tencent.tinker.lib.service.DefaultTinkerResultService;
import com.tencent.tinker.lib.service.PatchResult;
import com.tencent.tinker.lib.util.TinkerLog;
import com.tencent.tinker.lib.util.TinkerServiceInternals;

import java.io.File;

/**
 * 工程名 ： Tinker
 * 包名   ： com.example.ggxiaozhi.tinker.tinker
 * 作者名 ： 志先生_
 * 日期   ： 2018/01
 * 功能   ：决定在patch安装以后的后续操作 默认实现是杀死进程
 */

public class CustomResultService extends DefaultTinkerResultService {
    private static final String TAG = "Tinker.DefaultTinkerResultService";
    @Override
    public void onPatchResult(PatchResult result) {
        if (result == null) {
            TinkerLog.e(TAG, "DefaultTinkerResultService received null result!!!!");
            return;
        }
        TinkerLog.i(TAG, "DefaultTinkerResultService received a result:%s ", result.toString());

        //first, we want to kill the recover process
        TinkerServiceInternals.killTinkerPatchServiceProcess(getApplicationContext());

        // if success and newPatch, it is nice to delete the raw file, and restart at once
        // only main process can load an upgrade patch!
        if (result.isSuccess) {
            deleteRawPatchFile(new File(result.rawPatchFilePath));
        }
    }
}
