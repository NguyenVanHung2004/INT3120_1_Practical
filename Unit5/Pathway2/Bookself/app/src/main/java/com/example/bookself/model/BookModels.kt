package com.example.bookself.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VolumesResponse(
    val items: List<Volume> = emptyList()
)

@Serializable
data class Volume(
    @SerialName("id") val id: String,
    @SerialName("volumeInfo") val volumeInfo: VolumeInfo
)

@Serializable
data class VolumeInfo(
    @SerialName("title") val title: String = "",
    @SerialName("imageLinks") val imageLinks: ImageLinks? = null
)

@Serializable
data class ImageLinks(
    @SerialName("thumbnail") val thumbnail: String? = null,
    @SerialName("smallThumbnail") val smallThumbnail: String? = null
)
fun ImageLinks?.bestHttps(): String? {
    val raw = this?.thumbnail ?: this?.smallThumbnail
    return raw?.replace("http://", "https://")
}
