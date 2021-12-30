package com.example.android_semester_project

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_email.*
import java.lang.Exception

class EmailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email)

        sendEmailBtn.setOnClickListener {
            val recipient = recipentEt.text.toString().trim()
            val subject = subject.text.toString().trim()
            val message = messageEt.text.toString().trim()
            //sendEmail(recipient, subject, message)
            val emailIntent = Intent(Intent.ACTION_SENDTO,
            Uri.fromParts("mailto", "fa18-bse-083@cuiatk.edu.pk", null))
            startActivity(Intent.createChooser(emailIntent, "Send email...."))
        }

    }



    /*private fun sendEmail(recipient: String, subject: String, message: String) {
        val mIntent = Intent(Intent.ACTION_SENDTO)
        mIntent.setType("message/rfc822")
        mIntent.data = Uri.parse("mailto")
        mIntent.type = "text/plain"
        mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
        mIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
        mIntent.putExtra(Intent.EXTRA_TEXT, message)

        try {
            // start email intent
            startActivity(Intent.createChooser(mIntent, "Choose Email Client..."))
        }catch (e: Exception){
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }
    }*/
}