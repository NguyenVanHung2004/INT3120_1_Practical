package com.example.gridapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gridapp.R
import com.example.gridapp.data.GridItem

class GridAdapter(private val data: List<GridItem>) :
    RecyclerView.Adapter<GridAdapter.VH>() {

    class VH(view: View) : RecyclerView.ViewHolder(view) {
        val img: ImageView = view.findViewById(R.id.imgThumb)
        val title: TextView = view.findViewById(R.id.txtTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_grid, parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = data[position]
        holder.img.setImageResource(item.imageRes)
        holder.title.text = item.title
    }

    override fun getItemCount(): Int = data.size
}
