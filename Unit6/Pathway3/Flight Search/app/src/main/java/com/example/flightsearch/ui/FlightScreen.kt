// ui/FlightScreen.kt
package com.example.flightsearch.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.flightsearch.data.Airport
import com.example.flightsearch.data.Favorite

@Composable
fun FlightScreen(vm: FlightViewModel) {
    val state by vm.uiState.collectAsState()

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        OutlinedTextField(
            value = state.search,
            onValueChange = vm::onSearchChange,
            label = { Text("Nhập tên hoặc mã IATA (VD: HAN, SGN)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))

        if (state.search.isNotBlank()) {
            Text("Gợi ý", style = MaterialTheme.typography.titleMedium)
            LazyColumn {
                items(state.suggestions) { a ->
                    SuggestionRow(a) { vm.onPickDeparture(a) }
                }
            }
        } else if (state.favorites.isNotEmpty()) {
            Text("Yêu thích", style = MaterialTheme.typography.titleMedium)
            LazyColumn {
                items(state.favorites) { f -> FavoriteRow(f) }
            }
        }

        if (!state.selectedDeparture?.iataCode.isNullOrBlank()) {
            Spacer(Modifier.height(12.dp))
            Text(
                "Chuyến bay từ ${state.selectedDeparture!!.iataCode} - ${state.selectedDeparture!!.name}",
                style = MaterialTheme.typography.titleMedium
            )
            LazyColumn {
                items(state.results) { dest ->
                    ResultRow(
                        dep = state.selectedDeparture!!,
                        dest = dest,
                        onToggleFavorite = { /* TODO: gọi repo.toggleFavorite(...) */ }
                    )
                }
            }
        }
    }
}

@Composable
private fun SuggestionRow(a: Airport, onPick: () -> Unit) {
    ListItem(
        headlineContent = { Text("${a.iataCode} — ${a.name}") },
        supportingContent = { Text("Passengers: ${a.passengers}") },
        modifier = Modifier.clickable { onPick() }
    )
}

@Composable
private fun FavoriteRow(f: Favorite) {
    ListItem(
        headlineContent = { Text("${f.departure_code} → ${f.destination_code}") },
        supportingContent = { Text("Đã lưu") }
    )
}

@Composable
private fun ResultRow(dep: Airport, dest: Airport, onToggleFavorite: () -> Unit) {
    ListItem(
        headlineContent = { Text("${dep.iataCode} → ${dest.iataCode}") },
        supportingContent = { Text(dest.name) },
        trailingContent = {
            FilledTonalButton(onClick = onToggleFavorite) { Text("☆") }
        }
    )
}
