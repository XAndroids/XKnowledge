package com.android.xknowledge.sdk.component.activity.state

import android.os.Bundle
import android.util.Log
import com.android.xknowledge.R
import com.android.xknowledge.TitleActivity
import kotlinx.android.synthetic.main.activity_saveinstance.*

/**
 * Activity销毁数据恢复实践
 * 参考：https://cloud.tencent.com/developer/article/1661080
 */
class SaveInstanceActivity : TitleActivity() {
    private lateinit var myState: MyState

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("SaveInstance", "SaveInstanceActivity_onCreate")
        setContentView(R.layout.activity_saveinstance)

        //方案一：在onCreate中恢复状态，但是需要判断savedInstanceState!=null，因为不重建第一次进入的时候为null
//        myState = if (savedInstanceState != null) {
//            savedInstanceState.get("myState") as MyState
//        } else {
//            MyState();
//        }

        myState = MyState();

        saveinstance_button_add.setOnClickListener {
            saveinstance_textview_number.text = "${++myState.number}"
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        Log.i("SaveInstance", "SaveInstanceActivity_onRestoreInstanceState")
        super.onRestoreInstanceState(savedInstanceState)
        //方案二：在onRestoreInstanceState恢复装填，不需要判断savedInstanceState为null，只有重建时才回调
        myState = savedInstanceState.get("myState") as MyState;

    }

    override fun onResume() {
        super.onResume()
        saveinstance_textview_number.text = "${myState.number}"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.i("SaveInstance", "SaveInstanceActivity_onSaveInstanceState")
        outState.putSerializable("myState", myState);
        super.onSaveInstanceState(outState)
    }

    override fun onStop() {
        super.onStop()
    }
}
