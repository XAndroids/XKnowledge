package com.android.xknowledge.optimize.power;

import android.os.Bundle;
import android.view.View;

import com.android.xknowledge.R;
import com.android.xknowledge.TitleActivity;

/**
 * 电量优化：白名单实践、电量和充电状态监听
 * 参考：享学2《性能优化-电量和网络优化》
 */
public class PowerActivity extends TitleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power);
        findViewById(R.id.power_button_whitelist1).setOnClickListener(v ->
                BatteryUtils.addWhileList1(PowerActivity.this));
        findViewById(R.id.power_button_whitelist2).setOnClickListener(v ->
                BatteryUtils.addWhileList2(PowerActivity.this));
        findViewById(R.id.power_button_batterycheck).setOnClickListener(v ->
                BatteryUtils.checkBattery(PowerActivity.this));
        findViewById(R.id.power_button_batterysttus).setOnClickListener(v ->
                BatteryUtils.register(PowerActivity.this));
        findViewById(R.id.power_button_workmanager).setOnClickListener(v -> {
            BatteryUtils.doWork(PowerActivity.this);
        });
    }
}