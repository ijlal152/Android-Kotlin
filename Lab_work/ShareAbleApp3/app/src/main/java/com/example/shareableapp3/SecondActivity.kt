package com.example.shareableapp3

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_second.*
import java.lang.StringBuilder
import android.view.LayoutInflater as LayoutInflater
import android.view.MenuInflater as MenuInflater
import com.example.shareableapp3.SecondActivity as SecondActivity1

class SecondActivity : AppCompatActivity() {

    private lateinit var showdialog : Button
    private lateinit var builder: AlertDialog.Builder

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        showdialog = findViewById(R.id.showdialog)

        val showdialog = findViewById<Button>(R.id.showdialog)
        showdialog.setOnClickListener{
            val builder = AlertDialog.Builder(this)
            //set title for alert dialog
            builder.setTitle(R.string.dialogTitle)
            //set message for alert dialog
            builder.setMessage(R.string.dialogMessage)
            builder.setIcon(android.R.drawable.ic_dialog_alert)
            //performing positive action
            builder.setPositiveButton("Yes"){dialogInterface, which ->
                Toast.makeText(applicationContext,"clicked yes",Toast.LENGTH_LONG).show()
                System.exit(0)
            }
            //performing cancel action
            builder.setNeutralButton("Cancel"){dialogInterface , which ->
                Toast.makeText(applicationContext,"clicked cancel\n operation cancel",Toast.LENGTH_LONG).show()
            }
            //performing negative action
            builder.setNegativeButton("No"){dialogInterface, which ->
                Toast.makeText(applicationContext,"clicked No",Toast.LENGTH_LONG).show()
            }

            // Create the AlertDialog
            val alertDialog: AlertDialog = builder.create()
            // Set other dialog properties
            alertDialog.setCancelable(false)
            alertDialog.show()

        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val intent = getIntent()
        val name = intent.getStringExtra("Name")
        val age = intent.getIntExtra("Age", 0)

        resultTV1.text = "Name: $name"
        resultTV2.text = "Age: $age"
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.navigation_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuShare -> {
                val shareIntent = Intent().apply {
                    this.action = Intent.ACTION_SEND
                    this.putExtra(Intent.EXTRA_TEXT, "We are sharing data between two apps")
                    this.type = "text/plain"
                }
                startActivity(shareIntent)
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
        return true
    }







    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun onBackPressedBtn(view: android.view.View){
        val intent = Intent(this@SecondActivity, com.example.shareableapp3.MainActivity::class.java)
        startActivity(intent)
    }


}