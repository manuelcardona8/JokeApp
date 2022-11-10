package com.example.jokeapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.example.JokeData
import com.example.jokeapp.databinding.ItemRecyclerviewJokeBinding

class HomeHolder(
    private val parent: ViewGroup,
    private val binding: ItemRecyclerviewJokeBinding = ItemRecyclerviewJokeBinding.inflate(
        LayoutInflater.from(parent.context), parent, false
    )
) : RecyclerView.ViewHolder(binding.root) {

    fun bindModelToView(item: JokeData) {
        binding.textviewJoke.text = item.value
    }
}