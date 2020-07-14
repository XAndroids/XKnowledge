package com.android.xknowledge.thread

import android.os.AsyncTask
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.android.xknowledge.R
import com.android.xknowledge.TitleActivity

class AsyncTaskActivity : TitleActivity() {
    private lateinit var mShowTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asynctask)
        mShowTextView = findViewById(R.id.asynctask_textview_show)
        findViewById<Button>(R.id.asynctask_button_start).setOnClickListener {
            MyAsyncTask(mShowTextView).execute(3, 12)
        }
    }

    companion object {
        class MyAsyncTask(private val mShowTextView: TextView) : AsyncTask<Int, String, Int>() {
            override fun onPreExecute() {
                mShowTextView.text = "start,thread = ${Thread.currentThread().name}"
            }

            override fun doInBackground(vararg params: Int?): Int {
                val start = params[0]!!
                val end = params[1]!!
                for (i in start..end) {
                    publishProgress("$i,thread = ${Thread.currentThread().name}")
                    Thread.sleep(1000)
                }
                return end
            }

            override fun onProgressUpdate(vararg values: String?) {
                mShowTextView.text = values[0].toString()
            }

            override fun onPostExecute(result: Int?) {
                mShowTextView.text = "$result,thread = ${Thread.currentThread().name}"
            }
        }
    }
}
