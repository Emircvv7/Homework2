package com.example.homework_1.video

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework_1.databinding.ActivityVideoBinding
import com.example.homework_1.playlist.PlaylistItem
import com.example.homework_1.utils.Resource
import org.koin.androidx.viewmodel.ext.android.viewModel

class VideoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityVideoBinding.inflate(layoutInflater)
    }

    private val viewModel: VideoViewModel by viewModel()
    private lateinit var adapter: VideoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        adapter = VideoAdapter { videoItem ->
            onVideoItemSelected(videoItem)
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@VideoActivity)
            adapter = this@VideoActivity.adapter
        }

        val playlistItemId = intent.getStringExtra("playlistItemId")
        if (playlistItemId != null) {
            viewModel.getVideos(playlistItemId).observe(this, Observer { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        // Handle loading state
                    }
                    is Resource.Error -> {
                        // Handle error state
                    }
                    is Resource.Success -> {
                        resource.data?.let { videoItemsResponse ->
                            val videoItems = videoItemsResponse.items
                            adapter.submitList(videoItems)
                        }
                    }
                }
            })
        }
    }

    private fun onVideoItemSelected(videoItem: PlaylistItem) {
        // Handle video item selection
    }
}
