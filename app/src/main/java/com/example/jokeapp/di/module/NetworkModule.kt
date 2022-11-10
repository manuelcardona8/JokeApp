package com.example.jokeapp.di.module

import com.example.jokeapp.network.AddressApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    companion object{
        const val BASE_URL = "https://api.chucknorris.io/"
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit =
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun currentJokeApi(retrofit: Retrofit): AddressApi =
        retrofit.create(AddressApi::class.java)
}