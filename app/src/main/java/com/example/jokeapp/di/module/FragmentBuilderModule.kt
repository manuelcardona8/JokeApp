package com.example.jokeapp.di.module

import com.example.jokeapp.ui.home.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun provideHomeFragment(): HomeFragment

}