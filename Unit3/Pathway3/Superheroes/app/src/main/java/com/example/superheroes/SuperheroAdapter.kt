package com.example.superheroes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SuperheroAdapter(
    private val items: List<Superhero>,
    private val onClick: (Superhero) -> Unit = {}
) : RecyclerView.Adapter<SuperheroAdapter.Holder>() {

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val img: ImageView = itemView.findViewById(R.id.imgAvatar)
        private val name: TextView = itemView.findViewById(R.id.tvName)
        private val desc: TextView = itemView.findViewById(R.id.tvDesc)

        fun bind(item: Superhero) {
            name.text = item.name
            desc.text = item.description
            img.setImageResource(item.avatarResId)
            itemView.setOnClickListener { onClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_superhero, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) =
        holder.bind(items[position])

    override fun getItemCount(): Int = items.size
}