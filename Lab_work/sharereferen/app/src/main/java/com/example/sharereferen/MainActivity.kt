package com.example.sharereferen

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private var isRemembered = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPreferences = getSharedPreferences("SHARED_REF",Context.MODE_PRIVATE)
        isRemembered = sharedPreferences.getBoolean("CHECKBOX",false)

        if(isRemembered){
            val intent = Intent(this,anther::class.java)
            startActivity(intent)
            finish()
        }
        login.setOnClickListener{
            val name:String=name.text.toString()
            val age:Int=age.text.toString().toInt()
            val check:Boolean=check.isChecked
            val prefs=getSharedPreferences("SHARED_REF", MODE_PRIVATE)
            val editor =prefs.edit()
            editor.putString("Name",name)
            editor.putInt("Age",age)
            editor.putBoolean("CHECKBOX",check)
                editor.apply()
            Toast.makeText(this,"information safe", Toast.LENGTH_SHORT).show()

            val intent = Intent(this,anther::class.java)
            startActivity(intent)
            finish()
        }
    }
}