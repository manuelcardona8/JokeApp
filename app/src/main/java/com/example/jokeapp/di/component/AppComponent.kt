package com.example.jokeapp.di.component

import android.app.Application
import com.example.jokeapp.JokeApplication
import com.example.jokeapp.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuilderModule::class,
        NetworkModule::class,
        RepositoryBuilderModule::class,
        ViewModelModule::class,
        ViewModelFactoryModule::class,
        FragmentBuilderModule::class,
    ]
)

interface AppComponent : AndroidInjector<JokeApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }
}