package com.android.xknowledge.framework.aspectj;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

import com.android.xknowledge.R;
import com.android.xknowledge.XApplication;
import com.android.xknowledge.framework.aspectj.login.CheckLogin;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        radioGroup = (RadioGroup) findViewById(R.id.login2_login_radiogroup);
        radioGroup.setOnCheckedChangeListener(this);
        findViewById(R.id.login2_attention_textview).setOnClickListener(this);
        findViewById(R.id.login2_footprint_textview).setOnClickListener(this);
        findViewById(R.id.login2_shoppingcart_textview).setOnClickListener(this);
    }


    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.login2_login_radiobutton:
                XApplication.Companion.setLogin(true);
                break;
            case R.id.login2_nologin_radiobutton:
                XApplication.Companion.setLogin(false);
                break;
        }
    }

    @CheckLogin
    @Override
    public void onClick(View v) {
        String result = "";
        switch (v.getId()) {
            case R.id.login2_attention_textview:
                result = "已登录,点击了我的关注";
                break;
            case R.id.login2_footprint_textview:
                result = "已登录,点击了我的足迹";
                break;
            case R.id.login2_shoppingcart_textview:
                result = "已登录,点击了我的购物车";
                break;
        }
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
    }
}
