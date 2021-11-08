package com.example.custom_listview

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.custom_listview.databinding.ActivityMainBinding
import com.example.custom_listview.databinding.ActivityUserBinding
import com.example.custom_listview.databinding.ActivityUserBinding.*
import com.example.custom_listview.databinding.ActivityUserBinding.inflate as inflate

class UserActivity : AppCompatActivity(){
    private lateinit var binding: ActivityUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra("name")
        val phone = intent.getStringExtra("phone")
        val country = intent.getStringExtra("country")
        val imageId = intent.getIntExtra("imageId", R.drawable.avatar)

        binding.nameProfile.text = name
        binding.phoneProfile.text = phone
        binding.countryProfile.text = country
        binding.profileImage.setImageResource(imageId)

    }
}