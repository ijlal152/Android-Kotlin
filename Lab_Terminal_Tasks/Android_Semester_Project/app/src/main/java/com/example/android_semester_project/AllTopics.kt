package com.example.android_semester_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_all_topics.*

class AllTopics : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_topics)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val arrayList = ArrayList<Model1>()
        arrayList.add(Model1(id = 1, title="Material time picker"))
        arrayList.add(Model1(id = 2, title="Notification"))
        arrayList.add(Model1(id = 3, title="Send email"))
        arrayList.add(Model1(id = 4, title="Shared References"))
        arrayList.add(Model1(id = 5, title="Async task"))
        arrayList.add(Model1(id = 6, title="Async task loader"))
        arrayList.add(Model1(id = 7, title="Sending SMS"))
        arrayList.add(Model1(id = 8, title="Location"))
        arrayList.add(Model1(id = 9, title="Read and write file"))
        arrayList.add(Model1(id = 10, title="Services and Broad cast"))
        arrayList.add(Model1(id = 11, title="Volly library"))


        val myAdapter = MyAdapter1(arrayList, this)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = myAdapter
    }


    override fun onSupportNavigateUp(): Boolean {
        super.onBackPressed()
        return true
    }

    fun onBackPressedBtn(view: android.view.View){
        val intent = Intent(this@AllTopics, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}