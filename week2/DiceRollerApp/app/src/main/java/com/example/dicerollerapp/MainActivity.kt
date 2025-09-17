package com.example.dicerollerapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val buttonRoll = findViewById<Button>(R.id.btnRoll);
        val diceImage = findViewById<ImageView>(R.id.ivDice)
        val btnDemo = findViewById<Button>(R.id.btnDemo)
        buttonRoll.setOnClickListener {
            val value = Random.nextInt(1,7)

           val imageSource = when(value) {
                1 -> R.drawable.dice_1
                2 -> R.drawable.dice_2
                3 -> R.drawable.dice_3
                4 -> R.drawable.dice_4
                5 -> R.drawable.dice_5
                else -> R.drawable.dice_6
            }
            diceImage.setImageResource(imageSource)
        }

        btnDemo.setOnClickListener {
            val intent = Intent(this, ImplicitIntentDemoActivity::class.java)
            startActivity(intent)
        }



    }
}