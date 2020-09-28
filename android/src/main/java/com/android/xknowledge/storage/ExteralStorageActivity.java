package com.android.xknowledge.storage;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.xknowledge.R;
import com.android.xknowledge.TitleActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 应用外部存储私有目录访问测试
 */
public class ExteralStorageActivity extends TitleActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exteral_storage);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //没有授权，编写申请权限代码
            ActivityCompat.requestPermissions(ExteralStorageActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 100);
        } else {
            try {
                //其它应用外部存储私有目录测试
                //如果不管其它应用是否打开分区存储，只要自己没有打开分区存储就可以访问其它应用的外部存储私有目录
                //如果自己应用打开了分区存储，访问其它应用的外部存储私有目录数据会报错如下：
                //W/System.err: java.io.FileNotFoundException: /storage/emulated/0/Android/data/com.qunar.debug.storagetest/cache/test.txt: open failed: EACCES (Permission denied)
                //W/System.err:     at libcore.io.IoBridge.open(IoBridge.java:496)
                //        at java.io.FileInputStream.<init>(FileInputStream.java:159)
                //        at com.android.xknowledge.storage.ExteralStorageActivity.onCreate_aroundBody0(ExteralStorageActivity.java:36)
                //        at com.android.xknowledge.storage.ExteralStorageActivity.onCreate_aroundBody1$advice(ExteralStorageActivity.java:23)
                //W/System.err:     at com.android.xknowledge.storage.ExteralStorageActivity.onCreate(ExteralStorageActivity.java:1)
                String filePath = Environment.getExternalStorageDirectory().getPath() + File.separator + "Android" + File.separator + "data" + File.separator + "com.qunar.debug.storagetest" + File.separator + "cache" + File.separator + "test.txt";
                InputStream inputStream = new FileInputStream(new File(filePath));
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = bufferedReader.readLine();
                bufferedReader.close();//关闭输入流
                Log.i("storagetest", line);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}