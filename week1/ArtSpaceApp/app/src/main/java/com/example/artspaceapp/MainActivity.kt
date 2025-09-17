package com.example.artspaceapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
data class Artwork(
    val imageRes: Int,
    val title: String,
    val description: String
)
class MainActivity : AppCompatActivity() {
    private val artworks = listOf(
        Artwork(R.drawable.mona_lisa, "Mona Lisa", "Leonardo da Vinci, 1503"),
        Artwork(R.drawable.starry_night, "The Starry Night", "Vincent van Gogh, 1889"),
        Artwork(R.drawable.girl_pearl, "Girl with a Pearl Earring", "Johannes Vermeer, 1665")
    )

    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val artImage: ImageView = findViewById(R.id.artImage)
        val artTitle: TextView = findViewById(R.id.artTitle)
        val artDescription: TextView = findViewById(R.id.artDescription)
        val btnNext: Button = findViewById(R.id.btnNext)
        val btnPrevious: Button = findViewById(R.id.btnPrevious)

        fun updateArtwork() {
            val art = artworks[currentIndex]
            artImage.setImageResource(art.imageRes)
            artTitle.text = art.title
            artDescription.text = art.description
        }

        // init lần đầu
        updateArtwork()

        btnNext.setOnClickListener {
            currentIndex = (currentIndex + 1) % artworks.size
            updateArtwork()
        }

        btnPrevious.setOnClickListener {
            currentIndex = if (currentIndex - 1 < 0) artworks.size - 1 else currentIndex - 1
            updateArtwork()
        }
    }
}


