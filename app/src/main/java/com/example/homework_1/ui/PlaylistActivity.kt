package com.example.homework_1.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework_1.BuildConfig
import com.example.homework_1.video.VideoActivity
import com.example.homework_1.databinding.ActivityPlaylistBinding
import com.example.homework_1.playlist.PlaylistItem
import com.example.homework_1.utils.Resource
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlaylistActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityPlaylistBinding.inflate(layoutInflater)
    }

    private val viewModel: YouTubeViewModel by viewModel()
    private lateinit var adapter: PlaylistAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)

        adapter = PlaylistAdapter { playlistItem ->
            onPlaylistItemSelected(playlistItem)
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@PlaylistActivity)
            adapter = this@PlaylistActivity.adapter
        }

        val channelId = BuildConfig.YOUTUBE_CHANNEL_ID
        val part = "snippet,contentDetails"
        val maxResults = 25
        val apiKey = BuildConfig.YOUTUBE_API_KEY

        viewModel.getPlaylists(channelId, part, maxResults, apiKey).observe(this, Observer { resource ->
            when (resource) {
                is Resource.Loading -> {
                }
                is Resource.Error -> {
                }
                is Resource.Success -> {
                    resource.data?.let { playlistItemsResponse ->
                        val playlistItems = playlistItemsResponse.items
                        adapter.submitList(playlistItems)
                    }
                }
            }
        })
    }

    private fun onPlaylistItemSelected(playlistItem: PlaylistItem) {
        val intent = Intent(this, VideoActivity::class.java)
        intent.putExtra("playlistItemId", playlistItem.id)
        startActivity(intent)
    }
}
