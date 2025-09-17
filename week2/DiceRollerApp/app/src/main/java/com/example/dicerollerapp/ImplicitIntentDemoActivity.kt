package com.example.dicerollerapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ImplicitIntentDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_implicit_demo)

        val btnWeb = findViewById<Button>(R.id.btnWeb)
        val btnCall = findViewById<Button>(R.id.btnCall)
        val btnShare = findViewById<Button>(R.id.btnShare)

        btnWeb.setOnClickListener {
            val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://developer.android.com"))
            startActivity(webIntent)
        }

        btnCall.setOnClickListener {
            val callIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:+84123456789"))
            startActivity(callIntent)
        }

        btnShare.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, "I just rolled a dice!")
            }
            startActivity(Intent.createChooser(shareIntent, "Share via"))
        }
    }
}
