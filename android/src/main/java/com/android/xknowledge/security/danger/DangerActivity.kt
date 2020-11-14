package com.android.xknowledge.security.danger

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.android.xknowledge.R
import com.android.xknowledge.TitleActivity
import kotlinx.android.synthetic.main.activity_danger.*

class DangerActivity : TitleActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_danger)
        danger_button_call.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf( Manifest.permission.CALL_PHONE), 1);
            } else {
                //已授权，直接拨打电话
                call();
            }

        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray){
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