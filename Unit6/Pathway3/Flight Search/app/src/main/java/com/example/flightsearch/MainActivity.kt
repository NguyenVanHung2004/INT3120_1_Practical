package com.example.flightsearch

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels

import com.example.flightsearch.ui.FlightScreen
import com.example.flightsearch.ui.FlightViewModel
import com.example.flightsearch.ui.theme.FlightSearchTheme

class MainActivity : ComponentActivity() {
    private val vm by viewModels<FlightViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        val listRoot = assets.list("")?.toList().orEmpty()
        val listDbDir = try { assets.list("database")?.toList().orEmpty() } catch (_: Exception) { emptyList() }
        Log.d("ASSETS", "root: $listRoot")
        Log.d("ASSETS", "database/: $listDbDir")
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FlightSearchTheme {
                FlightScreen(vm)
            }
        }
    }
}
