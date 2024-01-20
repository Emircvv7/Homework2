package com.example.homework_1.di

import com.example.homework_1.video.VideoViewModel
import com.example.homework_1.ui.YouTubeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        YouTubeViewModel(get())
    }
    viewModel {
        VideoViewModel(get())
    }
}
