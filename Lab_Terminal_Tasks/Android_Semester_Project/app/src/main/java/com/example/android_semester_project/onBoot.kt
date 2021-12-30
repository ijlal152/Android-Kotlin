package com.example.android_semester_project

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class onBoot : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (Intent.ACTION_BOOT_COMPLETED == intent?.action){
            val intent1 = Intent(context, MainActivity.class::java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context?.startActivity(intent)
        }
    }
}