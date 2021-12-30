package com.example.android_semester_project

import android.annotation.SuppressLint
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_async_task.*

class AsyncTaskActivity : AppCompatActivity() {

    var progressAsyncTask: ProgressAsyncTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async_task)
        disableView(stopBtn, true)

        startBtn.setOnClickListener {
            progressAsyncTask = ProgressAsyncTask()
            pg.max = 60
            progressAsyncTask!!.execute(60)
        }
        stopBtn.setOnClickListener {
            progressAsyncTask?.cancel(true)
        }

    }

    fun disableView(view : View, isDisabled: Boolean){
        if(isDisabled){
            view.alpha = 0.5f
            view.isEnabled = false
        }else{
            view.alpha = 1f
            view.isEnabled = true
        }
    }




    @SuppressLint("StaticFieldLeak")
    inner class ProgressAsyncTask : AsyncTask<Int, Int, Void>(){

        override fun onPreExecute() {
            disableView(startBtn, true)
            disableView(stopBtn, false)
            super.onPreExecute()
        }


        override fun doInBackground(vararg params: Int?): Void? {
            if(params.isEmpty()){
                if(params[0] !=null){
                    for (i in 0..params[0]!!){
                        Thread.sleep(1000)
                        publishProgress(i)
                    }
                }
            }
            return null
        }

        override fun onProgressUpdate(vararg values: Int?) {
            if(values.isNotEmpty() && values[0] !=null)
                pg.progress = values[0]!!
            super.onProgressUpdate(*values)
        }

        override fun onPostExecute(result: Void?) {
            disableView(startBtn, false)
            disableView(stopBtn, true)
            super.onPostExecute(result)
        }

        override fun onCancelled() {
            disableView(startBtn, false)
            disableView(stopBtn, true)
            super.onCancelled()
        }

    }

}