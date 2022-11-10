package com.example.jokeapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.example.JokeData
import com.example.jokeapp.network.NetworkResponse
import com.example.jokeapp.repository.JokeRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val repository: JokeRepository
) : ViewModel() {

    private val _jokeResponse = MutableLiveData<NetworkResponse<JokeData>>()
    val jokeReponse: LiveData<NetworkResponse<JokeData>> by lazy { _jokeResponse }

    private val jokePersist = mutableListOf<JokeData>()

    fun fetchRandomJoke() {
        viewModelScope.launch {
            val response = repository.fetchRandomJoke()
            when (response) {
                is NetworkResponse.Error -> _jokeResponse.postValue(NetworkResponse.Error(response.error))
                is NetworkResponse.Loading -> _jokeResponse.postValue(NetworkResponse.Loading())
                is NetworkResponse.Success -> _jokeResponse.postValue(
                    NetworkResponse.Success(
                         addJoke(response.data)
                    )
                )
            }
        }
    }

    private fun addJoke(data: List<JokeData>): List<JokeData> {
        jokePersist.addAll(data)
        return jokePersist
    }
}