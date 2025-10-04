package com.example.superheroes

import android.content.res.Configuration
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import android.view.ViewGroup

import com.google.android.material.floatingactionbutton.FloatingActionButton
class MainActivity : AppCompatActivity() {

    private val allHeroes = DataSource.heroes()
    private var isSplit = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvHeroes = findViewById<RecyclerView>(R.id.rvHeroes)
        val layoutTeams = findViewById<View>(R.id.layoutTeams)
        val rvTeamA = findViewById<RecyclerView>(R.id.rvTeamA)
        val rvTeamB = findViewById<RecyclerView>(R.id.rvTeamB)
        val fab = findViewById<FloatingActionButton>(R.id.fabSplit)

        rvHeroes.layoutManager = LinearLayoutManager(this)
        rvHeroes.adapter = SuperheroAdapter(allHeroes)

        fab.setOnClickListener {
            if (!isSplit) {
                val (teamA, teamB) = splitTeams(allHeroes)

                rvTeamA.layoutManager = LinearLayoutManager(this)
                rvTeamA.adapter = SuperheroAdapter(teamA)

                rvTeamB.layoutManager = LinearLayoutManager(this)
                rvTeamB.adapter = SuperheroAdapter(teamB)

                rvHeroes.visibility = View.GONE
                layoutTeams.visibility = View.VISIBLE
                isSplit = true

                adjustOrientationLayout()  // <-- QUAN TRỌNG
            } else {
                rvHeroes.visibility = View.VISIBLE
                layoutTeams.visibility = View.GONE
                isSplit = false
            }
        }
    }

    private fun splitTeams(list: List<Superhero>): Pair<List<Superhero>, List<Superhero>> {
        val shuffled = list.shuffled()
        val half = shuffled.size / 2
        return shuffled.take(half) to shuffled.drop(half)
    }

    private fun adjustOrientationLayout() {
        val layoutTeams = findViewById<LinearLayout>(R.id.layoutTeams)
        val divider = findViewById<View>(R.id.dividerTeams)
        val containerA = findViewById<LinearLayout>(R.id.containerTeamA)
        val containerB = findViewById<LinearLayout>(R.id.containerTeamB)

        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // NGANG: trái – phải
            layoutTeams.orientation = LinearLayout.HORIZONTAL

            // Divider dọc
            divider.layoutParams = LinearLayout.LayoutParams(
                1, ViewGroup.LayoutParams.MATCH_PARENT
            )

            // MỖI BÊN CHIẾM 1/2: width=0, weight=1, height=match_parent
            containerA.layoutParams = LinearLayout.LayoutParams(
                0, ViewGroup.LayoutParams.MATCH_PARENT, 1f
            )
            containerB.layoutParams = LinearLayout.LayoutParams(
                0, ViewGroup.LayoutParams.MATCH_PARENT, 1f
            )
        } else {
            // DỌC: trên – dưới
            layoutTeams.orientation = LinearLayout.VERTICAL

            // Divider ngang
            divider.layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 1
            )

            // Mỗi khối cao 1/2: height=0, weight=1, width=match_parent
            containerA.layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0, 1f
            )
            containerB.layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0, 1f
            )
        }

        // Bắt buộc request layout sau khi đổi params
        containerA.requestLayout()
        containerB.requestLayout()
        divider.requestLayout()
        layoutTeams.requestLayout()
    }

    // Nếu bạn muốn tự xử lý khi xoay không bị recreate, thêm manifest (bên dưới) và giữ hàm này:
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (isSplit) adjustOrientationLayout()
    }
}