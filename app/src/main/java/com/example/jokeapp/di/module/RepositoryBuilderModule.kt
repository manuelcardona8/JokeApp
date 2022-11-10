package com.example.jokeapp.di.module

import com.example.jokeapp.network.AddressApi
import com.example.jokeapp.repository.JokeRepository
import com.example.jokeapp.repository.JokeRepositoryImp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryBuilderModule {

    @Provides
    @Singleton
    fun provideJokeRepositoryImp(addressApi: AddressApi): JokeRepository =
        JokeRepositoryImp(addressApi)
}