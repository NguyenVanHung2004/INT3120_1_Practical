package com.example.superheroes

import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager

import com.google.android.material.appbar.MaterialToolbar
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<MaterialToolbar>(R.id.topAppBar).apply {
            title = getString(R.string.app_name)
        }

        val rv = findViewById<RecyclerView>(R.id.rvHeroes)
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = SuperheroAdapter(DataSource.heroes())
    }
}