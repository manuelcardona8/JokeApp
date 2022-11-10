package com.example.jokeapp.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.example.JokeData
import com.example.jokeapp.MainCoroutineRule
import com.example.jokeapp.getOrAwaitValueTest
import com.example.jokeapp.network.NetworkResponse
import com.example.jokeapp.repository.JokeRepository
import com.example.jokeapp.ui.home.HomeViewModel
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: HomeViewModel

    @MockK
    private lateinit var repo: JokeRepository

    @Before
    fun initialize() {
        MockKAnnotations.init(this)
        viewModel = HomeViewModel(repo)
    }


    @Test
    fun `fetch a ramdon joke and return a Success`() = runBlocking {
        //GIVEN
        val jokedata = NetworkResponse.Success(
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
        coEvery { repo.fetchRandomJoke() }.returns(jokedata)

        //WHEN
        viewModel.fetchRandomJoke()
        val value = viewModel.jokeReponse.getOrAwaitValueTest()

        //THEN
        assertThat(value).isEqualTo(jokedata)
    }

    @Test
    fun `dont fetch a joke and return an Error`() = runBlocking {
        //GIVEN
        val expectedResult = NetworkResponse.Error<JokeData>(error = Throwable("Fake error"))
        coEvery { repo.fetchRandomJoke() }.returns(expectedResult)

        //WHEN
        viewModel.fetchRandomJoke()
        val value = viewModel.jokeReponse.getOrAwaitValueTest()

        //THEN
        assertThat(value).isEqualTo(expectedResult)
    }

}