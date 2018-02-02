package com.example.ggxiaozhi.hotfix;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final String FILE_END = ".apatch";//规定修复补丁的文件格式是.apatch文件
    private String mPatchDir;//修复补丁的存放路径

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //最后的文件所在路径为storage/emulated/0/Android/data/com.example.ggxiaozhi.hotfix/cache/apatch/
        mPatchDir = getExternalCacheDir().getAbsolutePath() + "/apatch/";
        //创建文件夹
        File file = new File(mPatchDir);
        if (!file.exists()) {
            file.mkdir();
        }

    }

    private void showToast() {
        boolean isShow = true;
        String str = "存在一个BUG";
        if (isShow) {
            str = "方法BUG修复完成";
        }
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    public void onClick(View view) {
        showToast();
    }

    public void onFix(View view) {
        AndFixPatchManager.getInstance().addPatch(getPatchName());
    }

    //加载修复文件的文件名
    public String getPatchName() {
        return mPatchDir.concat("andfix").concat(FILE_END);
    }
}
