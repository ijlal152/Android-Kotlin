package com.example.mongodb_crud_operation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.mongodb.App
import io.realm.mongodb.AppConfiguration

class MainActivity : AppCompatActivity() {

    val appID: String = "application-0-xnwmb"
    lateinit var uiThreadRealm: Realm
    val realmName: String = "My Project"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Realm.init(this)
        App(AppConfiguration.Builder(appID).build())
        Toast.makeText(this, "Succes", Toast.LENGTH_SHORT).show()

        //val config = RealmConfiguration.Builder()
        //    .name(realmName)
        //    .build()

    }
}