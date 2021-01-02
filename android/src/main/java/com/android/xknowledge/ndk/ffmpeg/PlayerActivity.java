package com.android.xknowledge.ndk.ffmpeg;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.xknowledge.R;

/**
 * FFmpeg播放时开发
 * 参考：享学1《NDK-FFmpeg播放器开发》
 */
public class PlayerActivity extends AppCompatActivity implements SurfaceHolder.Callback {
    private SurfaceView surfaceView;
    private Player player;
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager
                .LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.activity_player);

        surfaceView = findViewById(R.id.player_surfaceview_player);
        surfaceView.getHolder().addCallback(this);

        //初始化播放器
        player = new Player();

        //设置播放器监听器
        player.setOnPrepareListener(() -> player.start());
        player.setOnErrorListener(err -> Log.e("PlayerActivity", "err = " + err));
        player.setOnProgressListener(progress -> Log.i("PlayerActivity", "player = progress" + progress));

        //设置播放资源地址
        path = "/sdcard/01.flv";
        player.setDataSource(path);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //执行播放器准备工作
        player.prepare();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //执行播放停止工作
        player.stop();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        surfaceView.getHolder().removeCallback(this);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager
                    .LayoutParams.FLAG_FULLSCREEN);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setContentView(R.layout.activity_player);
        surfaceView = findViewById(R.id.player_surfaceview_player);
        surfaceView.getHolder().addCallback(this);
        player.setDataSource(path);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int i, int i1, int i2) {
        //将surface传入ndk用于底层视频的绘制
        player.setSurface(holder.getSurface());
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }
}