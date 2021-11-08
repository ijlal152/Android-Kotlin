package com.example.custom_listview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.custom_listview.databinding.ActivityMainBinding
import kotlin.arrayOf as arrayOf

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var userArrayList: ArrayList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imageId = intArrayOf(
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d,
            R.drawable.e,
            R.drawable.f,
        )

        val name = arrayOf(
            "Ijlal Hussain",
            "Bilal Hussain",
            "Ali Abbas",
            "Abu Bakkar",
            "Sikandar Khan",
            "Dilawar Khan"
        )

        val lastMessage = arrayOf(
            "Assalam o Alaikum",
            "Kaisy ho",
            "Kya ho raha h ?",
            "Ghar hn.",
            "Islamabad University",
            "Attock hn."
        )

        val lastmsgTime = arrayOf(
            "7:00 PM",
            "5:45 PM",
            "6:00 PM",
            "3:55 PM",
            "2:45 PM",
            "1:49 PM"
        )

        val phoneNo = arrayOf(
            "0324 9551586",
            "0321-1234567",
            "0321-1253467",
            "0321-1244567",
            "0321-1234566",
            "0321-1543567",
        )

        val country = arrayOf(
            "Pakistan",
            "Pakistan",
            "Pakistan",
            "Pakistan",
            "Pakistan",
            "Pakistan",
        )

        userArrayList = ArrayList()
        for(i in name.indices){
            val user = User(name[i], lastMessage[i], phoneNo[i], lastmsgTime[i], country[i],  imageId[i])
            userArrayList.add(user)
        }
        binding.listview.isClickable = true
        binding.listview.adapter = MyAdapter(this, userArrayList)
        binding.listview.setOnItemClickListener{
            parent, view, position, id ->
            val name = name[position]
            val phone = phoneNo[position]
            val country = country[position]
            val imageId = imageId[position]

            val i = Intent(this, UserActivity::class.java)
            i.putExtra("name", name)
            i.putExtra("phone", phone)
            i.putExtra("country", country)
            i.putExtra("imageId", imageId)

            startActivity(i)

        }
    }
}