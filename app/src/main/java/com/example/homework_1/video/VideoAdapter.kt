package com.example.homework_1.video

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homework_1.databinding.VideoItemBinding
import com.example.homework_1.playlist.PlaylistItem

class VideoAdapter(private val onClick: (PlaylistItem) -> Unit) :
    ListAdapter<PlaylistItem, VideoAdapter.VideoViewHolder>(VideoDiffCallback()) {

    class VideoViewHolder(val binding: VideoItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val binding = VideoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VideoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val videoItem = getItem(position)
        with(holder.binding) {
            videoTitle.text = videoItem.snippet.title
            videoDuration.text = videoItem.contentDetails.toString()

            Glide.with(videoThumbnail.context)
                .load(videoItem.snippet.thumbnails.default.url)
                .into(videoThumbnail)

            root.setOnClickListener { onClick(videoItem) }
        }
    }

    class VideoDiffCallback : DiffUtil.ItemCallback<PlaylistItem>() {
        override fun areItemsTheSame(oldItem: PlaylistItem, newItem: PlaylistItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PlaylistItem, newItem: PlaylistItem): Boolean {
            return oldItem == newItem
        }
    }
}
