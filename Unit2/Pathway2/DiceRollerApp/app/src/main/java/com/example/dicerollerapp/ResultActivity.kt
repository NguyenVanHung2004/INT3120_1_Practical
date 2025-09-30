package com.example.dicerollerapp
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val edtMessage = findViewById<EditText>(R.id.edtMessage)
        val btnSendBack = findViewById<Button>(R.id.btnSendBack)

        btnSendBack.setOnClickListener {
            val reply = edtMessage.text.toString()

            val resultIntent = Intent().apply {
                putExtra("reply_message", reply)
            }
            setResult(RESULT_OK, resultIntent)
            finish() // đóng activity và gửi kết quả về
        }
    }
}
