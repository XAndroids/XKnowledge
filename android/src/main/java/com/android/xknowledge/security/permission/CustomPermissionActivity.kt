package com.android.xknowledge.security.permission

import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.android.xknowledge.R
import com.android.xknowledge.TitleActivity
import kotlinx.android.synthetic.main.activity_custom_permission.*

/**
 * 定义权限页面
 * 参考：https://github.com/aimsio/android-custom-permission
 */
class CustomPermissionActivity : TitleActivity() {
    val permission_script_server = "com.aimsio.android.CONNECT_TO_SCRIPT_SERVER"
    val permission_form_data = "com.aimsio.android.READ_FORM_DATA"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_permission)

        custompermission_button_scriptserver.setOnClickListener {
            //普通
            if (checkCallingOrSelfPermission(permission_script_server) == PackageManager.PERMISSION_GRANTED) {
                showMessage("script server permission: granted")
            } else {
                showMessage("script server permission : denied")
            }
        }

        custompermission_button_formdata.setOnClickListener {
            if (checkCallingOrSelfPermission(permission_form_data) == PackageManager.PERMISSION_GRANTED) {
                showMessage("form data permission: granted")
            } else {
                showMessage("form data permission : denied")

                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(permission_form_data),
                    1
                );
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == 1) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showMessage("form data permission: granted")
            } else {
                showMessage("permission denied to read form data!")
            }
        }
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
