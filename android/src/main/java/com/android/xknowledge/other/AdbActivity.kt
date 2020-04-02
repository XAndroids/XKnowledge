package com.android.xknowledge.other

import android.os.Bundle
import android.util.Log
import com.android.xknowledge.R
import com.android.xknowledge.TitleActivity
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

/**
 * 在Java代码中自行adb shell命令
 * 参考：https://www.jianshu.com/p/7ad69274a68e
 */
class AdbActivity : TitleActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adb)

        var reader: BufferedReader? = null
        var content = ""
        try {
//            Runtime.getRuntime().exec("su")
            val process = Runtime.getRuntime().exec("ps -A")
            reader = BufferedReader(InputStreamReader(process.inputStream))
            val output = StringBuffer()
            var read: Int
            val buffer = CharArray(4096)
            while (reader.read(buffer).also { read = it } > 0) {
                output.append(buffer, 0, read)
            }
            reader.close()
            content = output.toString()
            Log.i("aaa", content);
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}