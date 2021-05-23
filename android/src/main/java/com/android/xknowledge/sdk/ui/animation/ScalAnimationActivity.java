package com.android.xknowledge.sdk.ui.animation;

import android.os.Bundle;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.android.xknowledge.R;
import com.android.xknowledge.TitleActivity;

/**
 * 补间动画实践
 */
public class ScalAnimationActivity extends TitleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scal_animation);
        ImageView imageView = findViewById(R.id.scale_imageview_test);

        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1);
        scaleAnimation.setDuration(300);
        scaleAnimation.setFillAfter(true);
        imageView.startAnimation(scaleAnimation);
    }
}