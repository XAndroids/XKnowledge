package com.android.xknowledge.framework.fresco

import android.graphics.drawable.Animatable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.xknowledge.R
import com.android.xknowledge.TitleActivity
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.controller.BaseControllerListener
import com.facebook.drawee.interfaces.DraweeController
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.image.ImageInfo

class GifListActivity : TitleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fresco_giflist)
        val recyclerAdapter = RecyclerAdapter()
        findViewById<RecyclerView>(R.id.fresco_recyclerview).apply {
            layoutManager = LinearLayoutManager(this.context)
            adapter = recyclerAdapter
        }
    }

    class RecyclerAdapter() :
        RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder>() {
        class RecyclerHolder(itemView: ViewGroup) :
            RecyclerView.ViewHolder(itemView) {
            internal var textView = itemView.findViewById<TextView>(R.id.fresco_item_textview)
            internal var simpleDraweeView =
                itemView.findViewById<SimpleDraweeView>(R.id.fresco_item_simpledraweeview)

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerHolder {
            Log.i("GifListActivity", "onCreateViewHolder viewType = $viewType")
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_fresco_item, parent, false)
            return RecyclerHolder(itemView as ViewGroup)
        }

        override fun getItemCount(): Int {
            return 20
        }

        override fun onBindViewHolder(holder: RecyclerHolder, position: Int) {
            Log.i("GifListActivity", "onBindViewHolder position = $position")
            holder.textView.text = "$position"

            val controllerListener = object : BaseControllerListener<ImageInfo>() {
                override fun onSubmit(id: String?, callerContext: Any?) {
                    //发起任何资源加载
                    Log.i("GifListActivity","onSubmit id = $id ,callerContext = ${callerContext.toString()}");
                }

                override fun onIntermediateImageSet(id: String?, imageInfo: ImageInfo?) {
                    //每次加载返回
                    Log.i("GifListActivity","onIntermediateImageSet id = $id ,imageInfo = ${imageInfo.toString()}");
                }

                override fun onFinalImageSet(id: String?, imageInfo: ImageInfo?, animatable: Animatable?) {
                    //最后一次加载返回
                    Log.i("GifListActivity","onFinalImageSet id = $id ,imageInfo = ${imageInfo.toString()} animatable = $animatable");
                    animatable!!.start();
                }

                override fun onRelease(id: String?) {
                    //释放资源
                    Log.i("GifListActivity","onRelease id = $id");
                }
            }
            val draweeController: DraweeController =
                Fresco.newDraweeControllerBuilder().setUri("https://raw.githubusercontent.com/penfeizhou/APNG4Android/master/app/src/main/assets/1.webp")
                    .setAutoPlayAnimations(false).setControllerListener(controllerListener).build()
            holder.simpleDraweeView.controller = draweeController;
        }
    }
}
