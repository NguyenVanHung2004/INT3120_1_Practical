// data/AirportDao.kt
package com.example.flightsearch.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AirportDao {
    // Autocomplete theo name hoặc iata_code, ưu tiên sân bay nhiều hành khách
    @Query("""
        SELECT * FROM airport
        WHERE name LIKE :q OR iata_code LIKE :q
        ORDER BY passengers DESC
        """)
    fun autocomplete(q: String): Flow<List<Airport>>

    // Lấy top phổ biến (khi cần gợi ý mặc định)
    @Query("SELECT * FROM airport ORDER BY passengers DESC LIMIT :limit")
    fun popularAirports(limit: Int = 10): Flow<List<Airport>>

    // Lấy danh sách điểm đến từ 1 sân bay (trừ chính nó)
    @Query("""
        SELECT * FROM airport
        WHERE iata_code != :departure
        ORDER BY passengers DESC
        """)
    fun destinationsFrom(departure: String): Flow<List<Airport>>
}
