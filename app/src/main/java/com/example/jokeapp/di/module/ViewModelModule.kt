package com.example.jokeapp.di.module

import androidx.lifecycle.ViewModel
import com.example.jokeapp.di.ViewModelKey
import com.example.jokeapp.ui.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun provideHomeViewModel(viewModel: HomeViewModel): ViewModel
}