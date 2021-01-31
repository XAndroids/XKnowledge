package com.android.xknowledge.ndk

import com.android.xknowledge.ListActivity
import com.android.xknowledge.ndk.base.BaseNdkActivity
import com.android.xknowledge.ndk.ffmpeg.FFmpegActivity
import com.android.xknowledge.ndk.ffmpeg.PlayerActivity
import com.android.xknowledge.ndk.mmap.MmapActivity

class NdkActivity : ListActivity() {
    override fun getMyListItemList(): List<ListItem> {
        return listOf(
            ListItem("Base", "ndk基本环境和使用", BaseNdkActivity::class.java),
            ListItem("FFmpeg", "FFmpeg第三方库接入", FFmpegActivity::class.java),
            ListItem("FFmpeg播放器", "FFmpeg播放器开发", PlayerActivity::class.java),
            ListItem("Mmap", "Mmap相关", MmapActivity::class.java)
        )
    }
}