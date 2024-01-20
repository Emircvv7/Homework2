package com.example.homework_1.playlist

data class PlaylistItem(
    val kind: String,
    val tag: String,
    val id: String,
    val snippet: Snippet,
    val playlistTitle: String,
    val playlistDescription: String,
    val contentDetails: ContentDetails
)
