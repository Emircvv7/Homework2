package com.example.homework_1

import android.app.Application
import com.example.homework_1.di.networkModule
import com.example.homework_1.di.repositoryModule
import com.example.homework_1.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(networkModule, repositoryModule, viewModelModule))
        }
    }
}
