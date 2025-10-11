package com.example.bookself.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookself.data.DefaultBooksRepository
import com.example.bookself.model.Volume
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
sealed interface UiState {
    object Loading : UiState
    data class Success(val books: List<Volume>) : UiState
    data class Error(val message: String) : UiState
}

class BookshelfViewModel(
    private val repo: DefaultBooksRepository = DefaultBooksRepository()
) : ViewModel() {

    var uiState by mutableStateOf<UiState>(UiState.Loading)
        private set

    var query by mutableStateOf("android")
        private set

    fun updateQuery(newQ: String) { query = newQ }


    fun load(q: String = query) {
        uiState = UiState.Loading
        viewModelScope.launch {
            uiState = try { UiState.Success(repo.search(q)) }
            catch (e: Exception) { UiState.Error(e.message ?: "Unknown error") }
        }
    }

    init { load() }
}
