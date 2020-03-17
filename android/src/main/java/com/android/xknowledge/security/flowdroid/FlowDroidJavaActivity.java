package com.android.xknowledge.security.flowdroid;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.android.xknowledge.R;
import com.android.xknowledge.TitleActivity;

import org.jetbrains.annotations.Nullable;

public class FlowDroidJavaActivity extends TitleActivity {
    public static final int PERMISSION_REQUEST_READ_PHONE_STATE = 0;

    private TextView mDeviceIdText;
    private TelephonyManager mTelephonyManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flowdroid);

        mDeviceIdText = findViewById(R.id.flowdroid_deviceid_textview);
        mTelephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

        findViewById(R.id.flowdroid_readstate_button).setOnClickListener(v -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                    requestReadPhoneStatePermission();
                } else {
                    mDeviceIdText.setText(mTelephonyManager.getDeviceId());
                }
            }
        });

        findViewById(R.id.flowdroid_call_button).setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_CALL);
            Uri data = Uri.parse("tel:" + 10010);
            intent.setData(data);
            startActivity(intent);
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_READ_PHONE_STATE) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mDeviceIdText.setText(mTelephonyManager.getDeviceId());
            }
        }
    }


    private void requestReadPhoneStatePermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE},
                PERMISSION_REQUEST_READ_PHONE_STATE);
    }
}
