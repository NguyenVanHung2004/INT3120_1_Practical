package com.example.waterme.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.waterme.Actions


class WateredReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Actions.ACTION_CUSTOM_WATERED) {
            val plant = intent.getStringExtra(Actions.EXTRA_PLANT_NAME) ?: "Unknown"
            Toast.makeText(context, "🌿 $plant đã được tưới xong!", Toast.LENGTH_SHORT).show()
        }
    }
}
