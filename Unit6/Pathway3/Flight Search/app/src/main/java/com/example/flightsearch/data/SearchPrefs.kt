// data/SearchPrefs.kt
package com.example.flightsearch.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore("search_prefs")
private val KEY_QUERY = stringPreferencesKey("search_query")

class SearchPrefs(private val context: Context) {
    val queryFlow = context.dataStore.data.map { it[KEY_QUERY] ?: "" }

    suspend fun saveQuery(q: String) {
        context.dataStore.edit { it[KEY_QUERY] = q }
    }
}
