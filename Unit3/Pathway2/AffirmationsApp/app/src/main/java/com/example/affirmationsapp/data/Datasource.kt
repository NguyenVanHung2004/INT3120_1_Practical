package com.example.affirmationsapp.data

import com.example.affirmationsapp.R

class Datasource {
    fun loadAffirmations(): List<Affirmation> {
        return listOf(
            Affirmation("I am strong", R.drawable.mona_lisa),
            Affirmation("I believe in myself", R.drawable.starry_night),
            Affirmation("I can achieve my goals", R.drawable.girl_pearl),
            Affirmation("I am learning and growing", R.drawable.mona_lisa),
            Affirmation("I am grateful for today", R.drawable.girl_pearl)
        )
    }
}