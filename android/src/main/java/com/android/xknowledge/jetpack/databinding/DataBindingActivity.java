package com.android.xknowledge.jetpack.databinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.android.xknowledge.R;
import com.android.xknowledge.databinding.ActivityDataBindingBinding;

/**
 * DataBinding数据绑定
 * 参考：https://www.jianshu.com/p/c56a987347ff
 */
public class DataBindingActivity extends AppCompatActivity {
    ActivityDataBindingBinding activityDataBindingBinding;
    //从model层来的数据
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //完成布局的解析，视图绑定，监听页面更新触发UI更新逻辑
        activityDataBindingBinding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding);

        //设置绑定的数据，当页面更新时且绑定数据已设置自动展示UI
        //创建并绑定观察者关系，当后续可观察数据类型更新时，通过观察者模式自动更新相关的视图
        user = new User("jett", "123");
        activityDataBindingBinding.setUser(user);

        //更新可观察数据源，触发观察者自动更新相关视图展示
        activityDataBindingBinding.databindingButtonUpdate.setOnClickListener(v -> user.setName(user
                .getName() + "1"));
    }
}