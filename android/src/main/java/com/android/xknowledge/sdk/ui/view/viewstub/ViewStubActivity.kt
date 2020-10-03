package com.android.xknowledge.sdk.ui.view.viewstub

import android.os.Bundle
import android.view.View
import android.view.ViewStub
import com.android.xknowledge.R
import com.android.xknowledge.TitleActivity

//ViewStub原理：在viewStub的parent删除viewStub，使用它的layoutParam添加layoutResource布局
class ViewStubActivity : TitleActivity() {
    private lateinit var mViewStubContainer: ViewStub

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewstub)

        mViewStubContainer = findViewById(R.id.viewstub_viewstubcontainer)
        //动态设置android:layout属性
        mViewStubContainer.layoutResource = R.layout.stub_view
    }

    fun inflateView(view: View) {
        //inflate()和visibility才解析layoutResource布局
//        mViewStubContainer.inflate()
        mViewStubContainer.visibility = View.VISIBLE
    }
}
