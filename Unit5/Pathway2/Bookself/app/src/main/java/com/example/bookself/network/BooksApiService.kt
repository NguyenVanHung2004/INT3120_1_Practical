package com.example.bookself.network

import com.example.bookself.model.VolumesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BooksApiService {
    @GET("volumes")
    suspend fun searchVolumes(
        @Query("q") query: String = "android",
        @Query("maxResults") max: Int = 20
    ): VolumesResponse
}