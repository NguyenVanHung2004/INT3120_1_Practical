package com.example.bookself.data

import com.example.bookself.model.Volume
import com.example.bookself.network.BooksApiService

interface BooksRepository {
    suspend fun search(query: String): List<Volume>
}

class DefaultBooksRepository(
    private val api: BooksApiService = NetworkModule.api
) : BooksRepository {
    override suspend fun search(query: String): List<Volume> =
        api.searchVolumes(query).items
}
