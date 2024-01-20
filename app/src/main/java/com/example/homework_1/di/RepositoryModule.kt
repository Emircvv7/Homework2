package com.example.homework_1.di

import com.example.homework_1.data.repository.YouTubeRepository
import org.koin.dsl.module

val repositoryModule = module {
    single {
        YouTubeRepository(get())
    }
}
