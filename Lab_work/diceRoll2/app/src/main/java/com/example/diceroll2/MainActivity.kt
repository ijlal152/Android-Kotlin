package com.example.diceroll2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        roll_btn.setOnClickListener{
            diceRoll()
        }
    }

    private fun diceRoll() {
        var randomNo = (1..6).random()
        var imageDice: Int
        when (randomNo) {
            1 -> {
                imageDice = R.drawable.ic_dice_one
            }
            2 -> {
                imageDice = R.drawable.ic_dice_two
            }
            3 -> {
                imageDice = R.drawable.ic_dice_three
            }
            4 -> {
                imageDice = R.drawable.ic_dice_four
            }
            5 -> {
                imageDice = R.drawable.ic_dice_five
            }
            else -> {
                imageDice = R.drawable.ic_dice_six
            }
        }
        dice_image.setImageResource(imageDice)
        Toast.makeText(this, "Dice is Rolled", Toast.LENGTH_SHORT).show()

    }
}