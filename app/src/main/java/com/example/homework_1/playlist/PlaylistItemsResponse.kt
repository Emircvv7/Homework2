package com.example.homework_1.playlist

data class PlaylistItemsResponse(
    val kind: String,
    val tag: String,
    val nextPageToken: String,
    val items: List<PlaylistItem>
)
