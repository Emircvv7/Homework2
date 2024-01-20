package com.example.homework_1.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.homework_1.data.repository.YouTubeRepository
import com.example.homework_1.playlist.PlaylistItemsResponse
import com.example.homework_1.utils.Resource

class YouTubeViewModel(private val repository: YouTubeRepository) : ViewModel() {

    fun getPlaylists(channelId: String, part: String, maxResults: Int, apiKey: String): LiveData<Resource<PlaylistItemsResponse>> {
        return repository.getPlaylists(channelId, part, maxResults, apiKey)
    }
}
