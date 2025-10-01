package com.example.superheroes

object DataSource {
    fun heroes(): List<Superhero> = listOf(
        Superhero("Superman", "Biểu tượng sức mạnh và công lý.", R.drawable.android_superhero1),
        Superhero("Batman", "Hiệp sĩ bóng đêm của Gotham.", R.drawable.android_superhero1),
        Superhero("Wonder Woman", "Chiến binh Amazon dũng mãnh.", R.drawable.android_superhero1),
        Superhero("Flash", "Nhanh hơn cả tia chớp.", R.drawable.android_superhero1),
        Superhero("Aquaman", "Vua Atlantis điều khiển biển cả.", R.drawable.android_superhero1),
        Superhero("Green Lantern", "Ý chí tạo nên sức mạnh.", R.drawable.android_superhero1)
    )
}