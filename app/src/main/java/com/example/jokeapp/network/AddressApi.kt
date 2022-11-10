package com.example.jokeapp.network

import com.example.example.JokeData
import retrofit2.http.GET

interface AddressApi {

    @GET("jokes/random")
    suspend fun getRandomJoke(): JokeData
}