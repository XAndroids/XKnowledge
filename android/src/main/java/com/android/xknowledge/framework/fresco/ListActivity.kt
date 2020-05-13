package com.android.xknowledge.framework.fresco

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.android.xknowledge.R
import com.android.xknowledge.TitleActivity
import com.facebook.drawee.view.SimpleDraweeView

class ListActivity : TitleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fresco)
        val dataSources = mutableListOf(
            "http://img0.imgtn.bdimg.com/it/u=3239102320,3312918336&fm=26&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=2859565705,2669619369&fm=26&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=1576466595,3496449229&fm=26&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=1047639406,4031278560&fm=26&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=2136237568,3085470234&fm=11&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=3362209742,853851658&fm=26&gp=0.jpg",
            "http://img0.imgtn.bdimg.com/it/u=3362209742,853851658&fm=26&gp=0.jpg",
            "http://img1.imgtn.bdimg.com/it/u=1873778166,3876004283&fm=26&gp=0.jpg",
            "http://img1.imgtn.bdimg.com/it/u=1302737274,2036920345&fm=11&gp=0.jpg",
            "http://img1.imgtn.bdimg.com/it/u=1198966512,3990143765&fm=26&gp=0.jpg",
            "http://img2.imgtn.bdimg.com/it/u=950794188,1233507686&fm=11&gp=0.jpg",
            "http://img2.imgtn.bdimg.com/it/u=2738003826,678888494&fm=26&gp=0.jpg",
            "http://img2.imgtn.bdimg.com/it/u=3219424832,1046532894&fm=26&gp=0.jpg",
            "http://img2.imgtn.bdimg.com/it/u=2828419515,3505980753&fm=26&gp=0.jpg",
            "http://img2.imgtn.bdimg.com/it/u=3267999425,2901796039&fm=26&gp=0.jpg",
            "http://img2.imgtn.bdimg.com/it/u=2749310931,2588587605&fm=26&gp=0.jpg",
            "http://img2.imgtn.bdimg.com/it/u=2725623363,3302302863&fm=26&gp=0.jpg",
            "http://img3.imgtn.bdimg.com/it/u=2373357173,1632558496&fm=11&gp=0.jpg",
            "http://img3.imgtn.bdimg.com/it/u=3071906142,1990737739&fm=26&gp=0.jpg",
            "http://img4.imgtn.bdimg.com/it/u=1036745067,4011189473&fm=26&gp=0.jpg",
            "http://img4.imgtn.bdimg.com/it/u=4235073254,3676166436&fm=11&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=1149190057,152324590&fm=11&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=1066168333,1217267098&fm=11&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=2613346950,3870034983&fm=26&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=440713863,3402054512&fm=26&gp=0.jpg",
            "http://img5.imgtn.bdimg.com/it/u=585728293,1720219144&fm=11&gp=0.jpg"
        )
        val recyclerAdapter =
            RecyclerAdapter(dataSources)

        findViewById<RecyclerView>(R.id.fresco_recyclerview).apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = recyclerAdapter

//            addOnScrollListener(object : OnScrollListener() {
//                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                    super.onScrollStateChanged(recyclerView, newState)
//                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//                        Fresco.getImagePipeline().resume();
//                    } else {
//                        Fresco.getImagePipeline().pause();
//                    }
//                }
//            })
        }
    }

    class RecyclerAdapter(private val dataSources: List<String>) :
        RecyclerView.Adapter<RecyclerAdapter.RecyclerHolder>() {
        class RecyclerHolder(itemView: ViewGroup) :
            RecyclerView.ViewHolder(itemView) {
            internal var textView = itemView.findViewById<TextView>(R.id.fresco_item_textview)
            internal var simpleDraweeView =
                itemView.findViewById<SimpleDraweeView>(R.id.fresco_item_simpledraweeview)

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerHolder {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.recycler_fresco_item, parent, false)
            return RecyclerHolder(itemView as ViewGroup)
        }

        override fun getItemCount(): Int {
            return dataSources.size
        }

        override fun onBindViewHolder(holder: RecyclerHolder, position: Int) {
            holder.textView.text = "$position"
            holder.simpleDraweeView.setImageURI(dataSources[position])
        }
    }
}
