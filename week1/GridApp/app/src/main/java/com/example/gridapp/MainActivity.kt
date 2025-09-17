package com.example.gridapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gridapp.adapter.GridAdapter
import com.example.gridapp.data.Datasource

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val rv = findViewById<RecyclerView>(R.id.gridRecycler)

        rv.layoutManager = GridLayoutManager(this, 2)
        rv.setHasFixedSize(true)
        rv.adapter = GridAdapter(Datasource().items())
    }
}