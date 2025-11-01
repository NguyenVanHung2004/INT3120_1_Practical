package com.example.waterme.ui

import android.content.Intent
import android.content.IntentFilter
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.waterme.Actions
import com.example.waterme.receivers.PowerReceiver
import com.example.waterme.receivers.WateredReceiver

/**
 * Gắn vào màn hình chính để lắng nghe:
 * - System broadcasts: POWER_CONNECTED / POWER_DISCONNECTED
 * - Local custom broadcast: ACTION_CUSTOM_WATERED
 */
@Composable
fun AttachBroadcastListeners() {
    val context = LocalContext.current

    // Receivers
    val powerReceiver = remember { PowerReceiver() }
    val wateredReceiver = remember { WateredReceiver() }

    // Đăng ký system broadcast (động)
    DisposableEffect(Unit) {
        val sysFilter = IntentFilter().apply {
            addAction(Intent.ACTION_POWER_CONNECTED)
            addAction(Intent.ACTION_POWER_DISCONNECTED)
        }
        context.registerReceiver(powerReceiver, sysFilter)

        onDispose {
            runCatching { context.unregisterReceiver(powerReceiver) }
        }
    }

    // Đăng ký local broadcast (chỉ trong app)
    DisposableEffect(Unit) {
        val localFilter = IntentFilter(Actions.ACTION_CUSTOM_WATERED)
        LocalBroadcastManager.getInstance(context)
            .registerReceiver(wateredReceiver, localFilter)

        onDispose {
            LocalBroadcastManager.getInstance(context)
                .unregisterReceiver(wateredReceiver)
        }
    }
}
