package com.example.android_semester_project

import android.Manifest
import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Telephony
import android.telephony.SmsManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_send_smsactivity.*


class SendSMSActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_smsactivity)

        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED )
        {
            ActivityCompat.requestPermissions(this,
            arrayOf(Manifest.permission.RECEIVE_SMS, Manifest.permission.SEND_SMS),
            111)
        }else{
            receiveMsg()
        }

        sendSMS.setOnClickListener {
            val sms = SmsManager.getDefault()
            sms.sendTextMessage(phoneNoEditTxt.text.toString(), "ME", msgtxt.text.toString(), null, null)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 111 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            receiveMsg()
    }

    private fun receiveMsg() {
        val br = object : BroadcastReceiver(){
            @SuppressLint("ObsoleteSdkInt")
            override fun onReceive(context: Context?, intent: Intent?) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
                    for(sms in Telephony.Sms.Intents.getMessagesFromIntent(intent)){
                        //Toast.makeText(applicationContext, sms.displayMessageBody, Toast.LENGTH_LONG).show()
                        phoneNoEditTxt.setText(sms.originatingAddress)
                        msgtxt.setText(sms.displayMessageBody)
                    }
                }
            }
        }

        registerReceiver(br, IntentFilter("android.provider.Telephony.SMS_RECEIVED"))

    }
}