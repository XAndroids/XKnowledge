package com.android.xknowledge.sdk.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.android.xknowledge.R;

public class BackgroundDialog extends Dialog {
    private Context context;

    public BackgroundDialog(@NonNull Context context, int pub_react_AlertViewStyle, int dialogHeight) {
        super(context);
        this.context = context;
    }

    public BackgroundDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.dialog_background_content);

        ImageView imageView = findViewById(R.id.dialog_backgroun_imageview);
        Animation anim = AnimationUtils.loadAnimation(this.context, R.anim.dialog_background_rotate);
        anim.setInterpolator(new LinearInterpolator());
        imageView.setAnimation(anim);

        //处理Dialog全屏之后，状态栏变黑的问题
        //参考：https://sing1.github.io/2018/03/01/Android_dialog_shadow/
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(this.context.getResources().getColor(R.color.design_default_color_primary));

    }
}
