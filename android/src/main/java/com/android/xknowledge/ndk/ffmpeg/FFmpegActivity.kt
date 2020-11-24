package com.android.xknowledge.ndk.ffmpeg

import android.os.Bundle
import com.android.xknowledge.R
import com.android.xknowledge.TitleActivity
import com.android.xknowledge.ndk.NativeLib
import kotlinx.android.synthetic.main.activity_ffmpeg.*

/**
 * NDK引入第三方库FFmpeg
 * 参考《享学1-cmake基础二-金狮》
 */
class FFmpegActivity : TitleActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ffmpeg)
//        ndk_ffmpeg_textview.text = "FFmpeg Vsersion = ${NativeLib().getFFmpegVersion()}"
    }
}
