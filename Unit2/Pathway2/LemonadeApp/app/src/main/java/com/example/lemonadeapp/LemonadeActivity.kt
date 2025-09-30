package com.example.lemonadeapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.lemonadeapp.R
import kotlin.random.Random

class LemonadeActivity :  AppCompatActivity() {

    private enum class State { TREE, SQUEEZE, DRINK, RESTART }

    private var state = State.TREE
    private var squeezesLeft = 0

    private lateinit var img: ImageView
    private lateinit var txt: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lemonade)

        img = findViewById(R.id.img)
        txt = findViewById(R.id.txt)

        // khôi phục state sau xoay màn (tuỳ chọn)
        state = savedInstanceState?.getString("state")?.let { State.valueOf(it) } ?: State.TREE
        squeezesLeft = savedInstanceState?.getInt("squeezesLeft") ?: 0
        updateUI()

        img.setOnClickListener {
            when (state) {
                State.TREE -> {
                    state = State.SQUEEZE
                    squeezesLeft = Random.nextInt(2, 5) // vắt 2–4 lần
                }
                State.SQUEEZE -> {
                    squeezesLeft--
                    if (squeezesLeft <= 0) state = State.DRINK
                }
                State.DRINK -> state = State.RESTART
                State.RESTART -> state = State.TREE
            }
            updateUI()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("state", state.name)
        outState.putInt("squeezesLeft", squeezesLeft)
        super.onSaveInstanceState(outState)
    }

    private fun updateUI() {
        when (state) {
            State.TREE -> {
                img.setImageResource(R.drawable.lemon_tree)
                txt.text = "Tap the tree to select a lemon"
            }
            State.SQUEEZE -> {
                img.setImageResource(R.drawable.lemon_squeeze)
                txt.text = "Keep tapping to squeeze the lemon (${squeezesLeft} left)"
            }
            State.DRINK -> {
                img.setImageResource(R.drawable.lemon_drink)
                txt.text = "Tap to drink your lemonade"
            }
            State.RESTART -> {
                img.setImageResource(R.drawable.lemon_restart)
                txt.text = "All done! Tap to start again"
            }
        }
    }
}
