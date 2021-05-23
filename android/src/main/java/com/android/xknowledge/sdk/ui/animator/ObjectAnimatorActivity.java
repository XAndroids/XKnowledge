package com.android.xknowledge.sdk.ui.animator;

import android.animation.FloatEvaluator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import com.android.xknowledge.R;
import com.android.xknowledge.TitleActivity;

/**
 * 属性动画Demo实践
 */
public class ObjectAnimatorActivity extends TitleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_object_animator);

        ImageView imageView = findViewById(R.id.objector_imageview_test);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView,
                "translationX", 0, 100).setDuration(1 * 1000);
        objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        objectAnimator.setEvaluator(new FloatEvaluator());
        objectAnimator.start();
    }
}