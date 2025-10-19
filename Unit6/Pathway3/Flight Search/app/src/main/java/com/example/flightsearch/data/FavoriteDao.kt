// data/FavoriteDao.kt
package com.example.flightsearch.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Insert
    suspend fun add(favorite: Favorite)

    @Query("DELETE FROM favorite WHERE departure_code = :dep AND destination_code = :dest")
    suspend fun remove(dep: String, dest: String)

    @Query("SELECT * FROM favorite")
    fun getAll(): Flow<List<Favorite>>

    @Query("""
        SELECT EXISTS(
            SELECT 1 FROM favorite
            WHERE departure_code = :dep AND destination_code = :dest
        )
        """)
    fun isFavorited(dep: String, dest: String): Flow<Boolean>
}
