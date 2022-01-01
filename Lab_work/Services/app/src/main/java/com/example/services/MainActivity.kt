package com.example.services

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnst.setOnClickListener{
            Intent(this,MyService::class.java).also {
                startService(it)
                txt1.text="Service has been started"
            }
        }
        btnstop.setOnClickListener{
            Intent(this,MyService::class.java).also {
                stopService(it)
                txt1.text="Service has been stoped"
            }
        }
        btnsend.setOnClickListener{
            Intent(this,MyService::class.java).also{
                val data= edit.text.toString()
                it.putExtra("Extra data",data)
                startService(it)

            }
        }
    }
}