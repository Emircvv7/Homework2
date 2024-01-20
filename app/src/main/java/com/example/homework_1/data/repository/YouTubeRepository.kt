package com.example.homework_1.data.repository

import androidx.lifecycle.LiveData
import com.example.homework_1.BuildConfig
import com.example.homework_1.data.network.YouTubeApiService
import com.example.homework_1.playlist.PlaylistItemsResponse
import com.example.homework_1.utils.Resource

class YouTubeRepository(private val apiService: YouTubeApiService) : BaseRepository() {

    fun getPlaylists(
        channelId: String = BuildConfig.YOUTUBE_CHANNEL_ID,
        part: String = "snippet,contentDetails",
        maxResults: Int = 25,
        apiKey: String = BuildConfig.YOUTUBE_API_KEY
    ): LiveData<Resource<PlaylistItemsResponse>> {
        return doRequest {
            try {
                val response = apiService.getPlaylists(part, channelId, maxResults, apiKey)
                Resource.Success(response)
            } catch (e: Exception) {
                Resource.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun getVideos(
        playlistId: String,
        part: String = "snippet,contentDetails",
        maxResults: Int = 25,
        apiKey: String = BuildConfig.YOUTUBE_API_KEY
    ): LiveData<Resource<PlaylistItemsResponse>> {
        return doRequest {
            try {
                val response = apiService.getVideos(part, playlistId, maxResults, apiKey)
                Resource.Success(response)
            } catch (e: Exception) {
                Resource.Error(e.message ?: "Unknown error")
            }
        }
    }
}
