// ui/FlightViewModel.kt
package com.example.flightsearch.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.flightsearch.data.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class UiState(
    val search: String = "",
    val suggestions: List<Airport> = emptyList(),
    val results: List<Airport> = emptyList(),
    val favorites: List<Favorite> = emptyList(),
    val selectedDeparture: Airport? = null
)

class FlightViewModel(app: Application) : AndroidViewModel(app) {
    private val db = FlightDatabase.get(app)
    private val repo = FlightRepository(db.airportDao(), db.favoriteDao())
    private val prefs = SearchPrefs(app)

    private val searchFlow = MutableStateFlow("")
    private val departureFlow = MutableStateFlow<Airport?>(null)

    val uiState: StateFlow<UiState> =
        combine(
            searchFlow.flatMapLatest { q ->
                if (q.isBlank()) flowOf(emptyList())
                else repo.autocomplete(q)
            },
            departureFlow.flatMapLatest { dep ->
                dep?.let { repo.destinationsFrom(it.iataCode) } ?: flowOf(emptyList())
            },
            repo.favorites(),
            searchFlow
        ) { suggestions, results, favs, search ->
            UiState(
                search = search,
                suggestions = suggestions,
                results = results,
                favorites = favs,
                selectedDeparture = departureFlow.value
            )
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), UiState())

    init {
        viewModelScope.launch {
            prefs.queryFlow.collect { q -> searchFlow.value = q }
        }
    }

    fun onSearchChange(q: String) {
        viewModelScope.launch { prefs.saveQuery(q) }
        searchFlow.value = q

        if (q.isBlank()) departureFlow.value = null
    }

    fun onPickDeparture(airport: Airport) {
        departureFlow.value = airport
    }
}
