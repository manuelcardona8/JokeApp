package com.example.jokeapp.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.example.JokeData
import com.example.jokeapp.MainCoroutineRule
import com.example.jokeapp.network.AddressApi
import com.example.jokeapp.network.NetworkResponse
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class JokeRepoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var repository: JokeRepositoryImp

    @MockK
    private lateinit var addressApi: AddressApi

    @Before
    fun initialize() {
        MockKAnnotations.init(this)
        repository = JokeRepositoryImp(addressApi)
    }

    @Test
    fun `call the api and return the joke`() = runBlocking {
        //GIVEN
        val jokedata = JokeData(
            arrayListOf<String>("123"),
            "2020-01-05 13:42:25.905626",
            "https://assets.chucknorris.host/img/avatar/chuck-norris.png",
            "kV_QezucTQWUjRREJ9RlCg",
            "2020-01-05 13:42:25.905626",
            "https://api.chucknorris.io/jokes/kV_QezucTQWUjRREJ9RlCg",
            "Why do extraterrestial travelers visit Earth? To worship Chuck Norris.",
        )
        val response = NetworkResponse.Success(
            listOf(JokeData(
                arrayListOf<String>("123"),
                "2020-01-05 13:42:25.905626",
                "https://assets.chucknorris.host/img/avatar/chuck-norris.png",
                "kV_QezucTQWUjRREJ9RlCg",
                "2020-01-05 13:42:25.905626",
                "https://api.chucknorris.io/jokes/kV_QezucTQWUjRREJ9RlCg",
                "Why do extraterrestial travelers visit Earth? To worship Chuck Norris.",
                ))
        )
        coEvery { addressApi.getRandomJoke() }.returns(jokedata)

        //WHEN
        val value = repository.fetchRandomJoke()

        //THEN
        assertThat(value).isEqualTo(response)
    }
}