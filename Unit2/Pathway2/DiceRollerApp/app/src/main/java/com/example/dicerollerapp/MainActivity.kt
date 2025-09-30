package com.example.dicerollerapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    var value = 0 ;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val buttonRoll = findViewById<Button>(R.id.btnRoll);
        val diceImage = findViewById<ImageView>(R.id.ivDice)
        val btnDemo = findViewById<Button>(R.id.btnDemo)

//        val username = intent.getStringExtra("username")
//        val age = intent.getIntExtra("age", -1)
//        val isStudent = intent.getBooleanExtra("isStudent", false)
//
//        val info = "Username: $username\nAge: $age\nIs student: $isStudent"
//
//        // Hiển thị Toast
//        Toast.makeText(this, info, Toast.LENGTH_LONG).show()

        buttonRoll.setOnClickListener {
             value = Random.nextInt(1,7)

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
            val intent = Intent(this, ImplicitIntentDemoActivity::class.java).apply{
                putExtra("value", value)
                putExtra("username", "Hung Nguyen")
                putExtra("age", 21)
                putExtra("isStudent", true)
            }

            startActivity(intent)
        }



    }
}