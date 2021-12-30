package com.example.android_semester_project

import android.animation.LayoutTransition
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Build.VERSION_CODES
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBar
import androidx.core.view.isVisible
import com.example.android_semester_project.model.Data

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val dataList: MutableList<Data> = mutableListOf()
    private lateinit var MyAdpter:MyAdpt

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent1 = Intent(this@MainActivity, RunServiceOnBoot::class.java)
        startService(intent1)

        checkImplementation_Btn.setOnClickListener {
            val intent = Intent(this, AllTopics::class.java)
            startActivity(intent)
            finish()
        }


        var detailsText: TextView? = null
        val layout: LinearLayout? = null

        //actionBar?.setTitle("Android Implementation")
        supportActionBar!!.setBackgroundDrawable(ColorDrawable(Color.parseColor("#146775")))
        supportActionBar!!.setTitle("Android Implementation")

        detailsText = findViewById<TextView>(R.id.details_text)
        //layout = findViewById<LinearLayout>(R.id.layout)
        layout?.getLayoutTransition()?.enableTransitionType(LayoutTransition.CHANGING)

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if(id == R.id.menuSettings){
            goToPreferenceActivity()
        }
        return super.onOptionsItemSelected(item)
    }
    private fun goToPreferenceActivity(){
        startActivity(Intent(this,Preference_Activity::class.java))
    }


















    @SuppressLint("NewApi")
    @RequiresApi(api = VERSION_CODES.KITKAT)
    fun expand(view: View) {
        val v = if (details_text!!.visibility == View.GONE) View.VISIBLE else View.GONE
        TransitionManager.beginDelayedTransition(layout, AutoTransition())
        details_text!!.visibility = v
    }











}



