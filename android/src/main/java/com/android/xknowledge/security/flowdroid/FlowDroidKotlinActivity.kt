package com.android.xknowledge.security.flowdroid

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.telephony.TelephonyManager
import android.widget.Button
import android.widget.TextView
import androidx.core.app.ActivityCompat
import com.android.xknowledge.R
import com.android.xknowledge.TitleActivity

const val PERMISSION_REQUEST_READ_PHONE_STATE = 0

class FlowDroidKotlinActivity : TitleActivity() {
    private lateinit var mDeviceIdText: TextView
    private lateinit var mTelephonyManager: TelephonyManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flowdroid)

        mDeviceIdText = findViewById(R.id.flowdroid_deviceid_textview);
        mTelephonyManager = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

        findViewById<Button>(R.id.flowdroid_readstate_button).setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.READ_PHONE_STATE
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    requestReadPhoneStatePermission()
                } else {
                    mDeviceIdText.text = mTelephonyManager.getDeviceId()
                }
            }
        }

        findViewById<Button>(R.id.flowdroid_call_button).setOnClickListener {
            val intent = Intent(Intent.ACTION_CALL);
            val data = Uri.parse("tel:" + 10010);
            intent.data = data;
            startActivity(intent);
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_READ_PHONE_STATE) {
            if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mDeviceIdText.text = mTelephonyManager.getDeviceId()
            }
        }
    }

    private fun requestReadPhoneStatePermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.READ_PHONE_STATE),
            PERMISSION_REQUEST_READ_PHONE_STATE
        )
    }
}