package com.android.xknowledge.sdk.component.activity.destroy

import android.os.Bundle
import com.android.xknowledge.R
import com.android.xknowledge.TitleActivity
import kotlinx.android.synthetic.main.activity_destroy.*

/**
 * Activity销毁数据恢复实践
 * 参考：https://cloud.tencent.com/developer/article/1661080
 */
class DestroyActivity : TitleActivity() {
    private lateinit var myState: MyState

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destroy)
        myState = if (savedInstanceState != null) {
            savedInstanceState.get("myState") as MyState;
        } else {
            MyState();
        }


        destroy_textview_number.text = "${myState.number}"
        destroy_button_add.setOnClickListener {
            destroy_textview_number.text = "${++myState.number}"
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putSerializable("myState", myState);
        super.onSaveInstanceState(outState)
    }
}
