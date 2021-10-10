package com.example.diceroll

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import kotlin.random.Random as Random


class MainActivity : AppCompatActivity() {
    private lateinit var imgDice:ImageView
    private val diceImages :MutableList<Int> = mutableListOf<Int>()
    private lateinit var animation: Animation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imgDice = findViewById(R.id.imgDice)
        val btnRoll = findViewById<Button>(R.id.btnRoll)
        btnRoll.setOnClickListener{
            getRandomValue()
        }
        // adding dice images to List
        diceImages.add(R.drawable.ic_dice_one)
        diceImages.add(R.drawable.ic_dice_two)
        diceImages.add(R.drawable.ic_dice_three)
        diceImages.add(R.drawable.ic_dice_four)
        diceImages.add(R.drawable.ic_dice_five)
        diceImages.add(R.drawable.ic_dice_six)

        // initialize the animation
        animation = AnimationUtils.loadAnimation(this@MainActivity, R.anim.shake_anim)

    }

    private  fun getRandomValue(){
        val random = java.util.Random().nextInt(6)
        imgDice.startAnimation(animation)
        // now we will get when he animations start and when it ends
        animation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationRepeat(p0: Animation?) {

            }
            override fun onAnimationEnd(p0: Animation?) {
                imgDice.setImageResource(diceImages.elementAt(random))  // this will set the dice image randomly
            }
            override fun onAnimationStart(p0: Animation?) {
                imgDice.setImageResource(R.drawable.ic_dice)
            }

        })

        imgDice.setImageResource(diceImages.elementAt(random)) // this will set the dice images

    }
}