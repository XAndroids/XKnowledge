package com.android.xknowledge.open.reactive.rxbinding

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.android.xknowledge.R
import com.android.xknowledge.TitleActivity
import com.jakewharton.rxbinding2.view.RxView
import com.tbruyelle.rxpermissions2.RxPermissions

class PermissionActivity : TitleActivity() {
    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission)

        val phoneButton = findViewById<Button>(R.id.permission_phone_button)
        val phoneButton2 = findViewById<Button>(R.id.permission_phone_button2)
        val cameraButton = findViewById<Button>(R.id.permission_camera_button)

        val rxPermission = RxPermissions(this)

        RxView.clicks(phoneButton).subscribe {
            rxPermission.request(Manifest.permission.CALL_PHONE).subscribe {
                if (it) {
                    val intent = Intent(Intent.ACTION_CALL)
                    intent.data = Uri.parse("tel:" + "10000")
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "授权失败", Toast.LENGTH_SHORT).show()
                }
            }
        }

        RxView.clicks(phoneButton2).compose(rxPermission.ensure(Manifest.permission.CALL_PHONE))
            .subscribe {
                if (it) {
                    val intent = Intent(Intent.ACTION_CALL)
                    intent.data = Uri.parse("tel:" + "10000")
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "授权失败", Toast.LENGTH_SHORT).show()
                }
            }

        RxView.clicks(cameraButton).compose(
            rxPermission.ensure(
                Manifest.permission.CAMERA,
                Manifest.permission.READ_CONTACTS
            )
        ).subscribe {
            if (it) {
                Toast.makeText(this, "打开相机成功", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "授权失败", Toast.LENGTH_SHORT).show()
            }
        }
    }
}