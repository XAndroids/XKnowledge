package com.example.xknowledge.ui.view.covered

import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.xknowledge.R
import com.example.xknowledge.TitleActivity

class CoveredActivity : TitleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_covered)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            val checkView1 = findViewById<View>(R.id.covered_checked1_view)
            Log.i(
                "CoveredActivity",
                "checkView1 isCovered = ${CoveredUtils.isViewCovered(checkView1)}"
            )

            val checkView2 = findViewById<View>(R.id.covered_checked2_view)
            Log.i(
                "CoveredActivity",
                "checkView2 isCovered = ${CoveredUtils.isViewCovered(checkView2)}"
            )

            val checkView3 = findViewById<View>(R.id.covered_checked3_view)
            Log.i(
                "CoveredActivity",
                "checkView3 isCovered = ${CoveredUtils.isViewCovered(checkView3)}"
            )


            val checkView4 = findViewById<View>(R.id.covered_checked4_view)
            Log.i(
                "CoveredActivity",
                "checkView4 isCovered = ${CoveredUtils.isViewCovered(checkView4)}"
            )
        }
    }
}
