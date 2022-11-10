package com.example.jokeapp.ui.home.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.example.JokeData

class HomeAdapter : RecyclerView.Adapter<HomeHolder>() {

    private val item = mutableListOf<JokeData>()

    fun setJoke(newItem: List<JokeData>) {
        item.addAll(newItem)
        notifyDataSetChanged()
    }

    fun cleanItem(){
        item.clear()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
        return HomeHolder(parent)
    }

    override fun onBindViewHolder(holder: HomeHolder, position: Int) {
        holder.bindModelToView(item[position])
    }

    override fun getItemCount() = item.size
}