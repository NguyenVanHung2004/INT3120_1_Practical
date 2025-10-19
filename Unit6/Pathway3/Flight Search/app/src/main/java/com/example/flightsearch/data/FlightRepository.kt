// data/FlightRepository.kt
package com.example.flightsearch.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

class FlightRepository(
    private val airportDao: AirportDao,
    private val favoriteDao: FavoriteDao
) {
    fun autocomplete(q: String): Flow<List<Airport>> =
        airportDao.autocomplete("%$q%")

    fun destinationsFrom(departureCode: String): Flow<List<Airport>> =
        airportDao.destinationsFrom(departureCode)

    fun favorites(): Flow<List<Favorite>> = favoriteDao.getAll()

    suspend fun toggleFavorite(dep: String, dest: String) {
        // Có thể truy vấn trước để biết đang tồn tại hay không, rồi add/remove
        // Ở đây để đơn giản, gọi remove rồi add tùy theo luồng bên UI (hoặc bạn dùng isFavorited để nhánh logic)
    }
}
