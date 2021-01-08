package com.android.xknowledge.jetpack.livedata

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.android.xknowledge.R
import com.android.xknowledge.TitleActivity
import kotlinx.android.synthetic.main.activity_livedata.*
import kotlinx.android.synthetic.main.activity_viewmodel.*

/**
 * LiveData实践
 * 参考：https://chiclaim.blog.csdn.net/article/details/104334179
 */
class LiveDataActivity : TitleActivity() {
    private lateinit var myViewModel: MyViewModel;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_livedata)
        myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        myViewModel.number.observe(this, Observer {
            livedata_textview_number.text = "$it"
        })
        livedata_button_add.setOnClickListener {
            myViewModel.plus(1)
        }
    }
}
