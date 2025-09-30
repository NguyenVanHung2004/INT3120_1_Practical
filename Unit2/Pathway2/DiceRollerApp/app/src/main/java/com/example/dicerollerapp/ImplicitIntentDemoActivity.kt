package com.example.dicerollerapp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import androidx.core.net.toUri

class ImplicitIntentDemoActivity : AppCompatActivity() {


    private val getResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val reply = result.data?.getStringExtra("reply_message")
            Toast.makeText(this, "Received: $reply", Toast.LENGTH_LONG).show()
        }
    }
   // private val REQUEST_CODE_RESULT = 1001
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_implicit_demo)

       val btnWeb = findViewById<Button>(R.id.btnWeb)
       val btnCall = findViewById<Button>(R.id.btnCall)
       val btnShare = findViewById<Button>(R.id.btnShare)
       val btnResult = findViewById<Button>(R.id.btnResult)

       // Bật nút back trên ActionBar
       val toolbar = findViewById<MaterialToolbar>(R.id.topAppBar)
       setSupportActionBar(toolbar)
       toolbar.setNavigationOnClickListener {
           finish() // đóng Activity và quay lại màn hình trước
       }

        val value = intent.getIntExtra("value", 0)
        Toast.makeText(this, "Value of dice: " +
                "$value", Toast.LENGTH_LONG).show()


        btnWeb.setOnClickListener {
            val webIntent = Intent(Intent.ACTION_VIEW, "https://developer.android.com".toUri())
            startActivity(webIntent)
        }

        btnCall.setOnClickListener {
            val callIntent = Intent(Intent.ACTION_DIAL, "tel:+84123456789".toUri())
            startActivity(callIntent)
        }

        btnShare.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, "I just rolled a dice!")
            }
            startActivity(Intent.createChooser(shareIntent, "Share via"))
        }

//         val REQUEST_CODE_RESULT = 1001
//        // Mở ResultActivity để nhận dữ liệu cach cu
//        btnResult.setOnClickListener {
//            val intent = Intent(this, ResultActivity::class.java)
//            startActivityForResult(intent, REQUEST_CODE_RESULT)
//        }

       btnResult.setOnClickListener {
           val intent = Intent(this, ResultActivity::class.java)
           getResult.launch(intent)
       }

    }
//    val REQUEST_CODE_RESULT = 1001
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (requestCode == REQUEST_CODE_RESULT && resultCode == Activity.RESULT_OK) {
//            val reply = data?.getStringExtra("reply_message")
//            Toast.makeText(this, "Received: $reply", Toast.LENGTH_LONG).show()
//        }
//    }

}
