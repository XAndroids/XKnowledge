package com.android.xknowledge.security.danger

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.android.xknowledge.R
import com.android.xknowledge.TitleActivity
import kotlinx.android.synthetic.main.activity_danger.*

/**
 * 动态申请危险权限
 */
class DangerActivity : TitleActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_danger)
        danger_button_call.setOnClickListener {
            //checkSelfPermission()：检查应用是否具有某个危险权限，如果有返回PERMISSION_GRANTED，如果没有则
            //返回PERMISSION_DENIED，且应用必须向用户申请
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) !=
                PackageManager.PERMISSION_GRANTED) {
                Log.i("DangerActivity","DangerActivity_checkSelfPermission() = false")
                //shouldShowRequestPermissionRationale()：如果应用之前请求过此权限但用户拒绝了请求，此方法
                //将返回 true。如果用户在过去拒绝了权限请求，并在 权限请求系统对话框中选择了 Don't ask again
                //选项，此方法将返回 false。如果设备规范禁止应用具 有该权限，此方法也会返回 false。
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        this, Manifest.permission.CALL_PHONE)) {
                    //requestPermissions()：应用可以通过这个方法动态申请权限，调用后会弹出一个对话框提示用户授
                    //权所申请的权限。
                    Log.i("DangerActivity","DangerActivity_shouldShowRequestPermissionRationale() = true")
                    ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission
                        .CALL_PHONE), 1);
                } else {
                    //用户拒绝过了，且不要再次询问
                    Log.i("DangerActivity","DangerActivity_shouldShowRequestPermissionRationale() = false")
                }
            } else {
                //已授权，直接拨打电话
                Log.i("DangerActivity","DangerActivity_checkSelfPermission() = true")
                call();
            }

        }
    }

    //onRequestPermissionsResult()：当应用请求权限时，系统将向用户显示一个对话框。当用户响应时，系统将调用应用的
    //onRequestPermissionsResult()方法，向其传递用户响应，处理对应的场景。
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        Log.i("DangerActivity", "DangerActivity_onRequestPermissionsResult(),requestCode " +
                "= $requestCode")
        when (requestCode) {
            1 -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //用户同意权限，拨打电话
                call()
            } else {
                //用户不同意权限，提示用户
                Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT)
                    .show()
            }
            else -> {
            }
        }
    }

    private fun call() {
        try {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:10086")
            startActivity(intent)
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }
}