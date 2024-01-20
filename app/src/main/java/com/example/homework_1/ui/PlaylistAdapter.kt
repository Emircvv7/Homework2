package com.example.homework_1.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homework_1.databinding.PlaylistItemBinding
import com.example.homework_1.playlist.PlaylistItem

class PlaylistAdapter(private val onClick: (PlaylistItem) -> Unit) :
    ListAdapter<PlaylistItem, PlaylistAdapter.PlaylistViewHolder>(PlaylistDiffCallback()) {

    class PlaylistViewHolder(val binding: PlaylistItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        val binding = PlaylistItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PlaylistViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        val playlistItem = getItem(position)
        with(holder.binding) {
            playlistTitle.text = playlistItem.snippet.title
            playlistVideoCount.text = "${playlistItem.contentDetails.itemCount} video series"

            Glide.with(playlistImage.context)
                .load(playlistItem.snippet.thumbnails.default.url)
                .into(playlistImage)

            root.setOnClickListener { onClick(playlistItem) }
        }
    }

    class PlaylistDiffCallback : DiffUtil.ItemCallback<PlaylistItem>() {
        override fun areItemsTheSame(oldItem: PlaylistItem, newItem: PlaylistItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PlaylistItem, newItem: PlaylistItem): Boolean {
            return oldItem == newItem
        }
    }
}
