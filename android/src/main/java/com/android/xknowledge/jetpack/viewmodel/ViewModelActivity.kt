package com.android.xknowledge.jetpack.viewmodel

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.android.xknowledge.R
import com.android.xknowledge.TitleActivity
import kotlinx.android.synthetic.main.activity_viewmodel.*

/**
 * 参考：https://chiclaim.blog.csdn.net/article/details/104200091
 */
class ViewModelActivity : TitleActivity() {
    private lateinit var myViewModel: MyViewModel;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewmodel)
        myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        viewmodel_textview_number.text = "${myViewModel.number}"
        viewmodel_button_add.setOnClickListener {
            viewmodel_textview_number.text = "${++myViewModel.number}"
        }
    }
}
