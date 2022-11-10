package com.example.jokeapp.di.module

import com.example.jokeapp.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(
        modules = [
            FragmentBuilderModule::class,
            ViewModelModule::class,
        ])

    abstract fun contributeMainActivity(): MainActivity
}