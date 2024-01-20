package com.example.homework_1.video

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.homework_1.data.repository.YouTubeRepository
import com.example.homework_1.playlist.PlaylistItemsResponse
import com.example.homework_1.utils.Resource

class VideoViewModel(private val repository: YouTubeRepository) : ViewModel() {

    fun getVideos(playlistId: String): LiveData<Resource<PlaylistItemsResponse>> {
        return repository.getVideos(playlistId)
    }
}

