package com.example.ggxiaozhi.tinkerdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.ggxiaozhi.tinkerdemo.utils.TinkerManager;
import com.tencent.tinker.lib.tinker.TinkerInstaller;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private static final String FILE_END = ".apk";//文件后缀
    private String FILEDIR;//文件路径
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //    /storage/emulated/0/Android/data/com.example.ggxiaozhi.tinker/cache/tpatch/
        FILEDIR = getExternalCacheDir().getAbsolutePath() + "/tpatch/";
        //创建路径对应的文件夹
        File file = new File(FILEDIR);
        if (!file.exists())
            file.mkdir();
    }

    public void loadPatch(View view) {
        TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(), getPatchName());
    }

    public String getPatchName() {
        return FILEDIR.concat("patch_signed_7zip").concat(FILE_END);
    }

    public void onToast(View view) {
        Toast.makeText(this, "fix", Toast.LENGTH_SHORT).show();
    }
}
