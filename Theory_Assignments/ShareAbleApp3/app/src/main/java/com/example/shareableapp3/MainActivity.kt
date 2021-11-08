package com.example.shareableapp3

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*
//import com.example.shareableapp3.SecondActivity as SecondActivity::class.java

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        exitbtn.setOnClickListener{
            System.exit(0)
        }
    }

    fun onSaveButtonClick(view: android.view.View) {
        // collecting data from text fields

        val name = nametxt.text.toString()
        val age = agetxt.text.toString().toInt()

        // start intent for new activity and pass data

        val intent = Intent(this@MainActivity, SecondActivity::class.java)
        intent.putExtra("Name", name)
        intent.putExtra("Age", age)
        startActivity(intent)
    }


}