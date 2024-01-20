package com.example.homework_1.data.network

import com.example.homework_1.playlist.PlaylistItemsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface YouTubeApiService {

    @GET("playlists")
    suspend fun getPlaylists(
        @Query("part") part: String,
        @Query("channelId") channelId: String,
        @Query("maxResults") maxResults: Int,
        @Query("key") apiKey: String
    ): PlaylistItemsResponse

    @GET("playlistItems")
    suspend fun getVideos(
        @Query("part") part: String,
        @Query("playlistId") playlistId: String,
        @Query("maxResults") maxResults: Int,
        @Query("key") apiKey: String
    ): PlaylistItemsResponse
}
