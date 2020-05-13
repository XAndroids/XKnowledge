package com.android.xknowledge.framework.fresco

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.xknowledge.R
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.interfaces.DraweeController
import com.facebook.drawee.view.SimpleDraweeView

class GifActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gif)

        val simpleDraweeView =
            findViewById<SimpleDraweeView>(R.id.frescogif_simpledraweeView)
        val uri = Uri.parse("asset:///test.gif")
        val draweeController: DraweeController = Fresco.newDraweeControllerBuilder().setUri(uri)
            .setAutoPlayAnimations(true).build()
        simpleDraweeView.controller = draweeController
    }
}
