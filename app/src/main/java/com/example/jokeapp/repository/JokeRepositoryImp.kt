package com.example.jokeapp.repository

import com.example.example.JokeData
import com.example.jokeapp.network.AddressApi
import com.example.jokeapp.network.NetworkResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class JokeRepositoryImp(
    private val addressApi: AddressApi,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : JokeRepository {

    override suspend fun fetchRandomJoke(): NetworkResponse<JokeData> =
        withContext(dispatcher) {
            try {
                val response = addressApi.getRandomJoke()
                return@withContext NetworkResponse.Success(listOf(response))
            } catch (e: Throwable) {
                return@withContext NetworkResponse.Error(e)
            }
        }
}