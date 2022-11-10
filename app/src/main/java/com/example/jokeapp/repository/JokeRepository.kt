package com.example.jokeapp.repository

import com.example.example.JokeData
import com.example.jokeapp.network.NetworkResponse

interface JokeRepository {

    suspend fun fetchRandomJoke(): NetworkResponse<JokeData>
}