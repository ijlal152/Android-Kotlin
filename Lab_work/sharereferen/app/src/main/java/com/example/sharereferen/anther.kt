package com.example.sharereferen

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_anther.*
import java.util.prefs.AbstractPreferences

class anther : AppCompatActivity() {

    private lateinit var preferences:SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anther)

        preferences = getSharedPreferences("SHARED_REF",Context.MODE_PRIVATE)

        val name = preferences.getString("Name","")

        name1.text = name

        val age = preferences.getInt("Age",0)
        age1.text = ""+age

        logout.setOnClickListener{
            val editor:SharedPreferences.Editor = preferences.edit()
            editor.clear()
            editor.apply()


            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}