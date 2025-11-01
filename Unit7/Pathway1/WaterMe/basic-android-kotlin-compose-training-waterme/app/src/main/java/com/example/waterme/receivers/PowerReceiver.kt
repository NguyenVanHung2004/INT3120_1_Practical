package com.example.waterme.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class PowerReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val text = when (intent.action) {
            Intent.ACTION_POWER_CONNECTED -> "🔌 Đã cắm sạc (Power Connected)"
            Intent.ACTION_POWER_DISCONNECTED -> "⚡ Rút sạc (Power Disconnected)"
            else -> null
        }
        text?.let { Toast.makeText(context, it, Toast.LENGTH_SHORT).show() }
    }
}
