package com.android.xknowledge.framework.fresco

import android.graphics.drawable.Animatable
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.android.xknowledge.R
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.controller.BaseControllerListener
import com.facebook.drawee.interfaces.DraweeController
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.image.ImageInfo

/**
 * Fresco是否支持pause和resume功能
 * com.facebook.imagepipeline.animated.base.AbstractAnimatedDrawable只有在1.3.0（以前）有；
 * com/facebook/fresco/animation/drawable/AnimatedDrawable2只有在2.2.0（以上）支持；
 * Fresco的兼容性，不敢恭维！！！！
 * 参考：
 *  https://blog.csdn.net/qq_31387043/article/details/75258380
 *  https://github.com/facebook/fresco/issues/1529
 */
class GifActivity : AppCompatActivity() {
    private lateinit var animatable1: Animatable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gif)

        val simpleDraweeView =
            findViewById<SimpleDraweeView>(R.id.frescogif_simpledraweeView)
        val controllerListener = object : BaseControllerListener<ImageInfo>() {
            override fun onFinalImageSet(
                id: String?,
                imageInfo: ImageInfo?,
                animatable: Animatable?
            ) {
                animatable1 = animatable!!
            }
        }

        val draweeController: DraweeController =
            Fresco.newDraweeControllerBuilder().setUri("asset:///test1.gif")
                .setAutoPlayAnimations(false).setControllerListener(controllerListener).build()
        simpleDraweeView.controller = draweeController

        val startButton = findViewById<Button>(R.id.frescogif_button_start)
        startButton.setOnClickListener {
            animatable1.start();
        }
        val stopButton = findViewById<Button>(R.id.frescogif_button_stop)
        stopButton.setOnClickListener {
            animatable1.stop();
        }
    }
}
