package com.inspirationdriven.uhomework.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.uhomework.databinding.ListItemCatThumbnailBinding
import com.inspirationdriven.uhomework.model.CatMeta

class CatPhotoAdapter(private val layoutInflater: LayoutInflater, private val adapterCallbacks: AdapterCallbacks) :
    ListAdapter<CatMeta, CatThumbnailViewHolder>(AsyncDifferConfig.Builder(object :
        DiffUtil.ItemCallback<CatMeta>() {
        override fun areItemsTheSame(oldItem: CatMeta, newItem: CatMeta) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: CatMeta, newItem: CatMeta) =
            oldItem.id == newItem.id

    }).build()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CatThumbnailViewHolder(ListItemCatThumbnailBinding.inflate(layoutInflater, parent, false)){
            adapterCallbacks.onThumbnailClick(it)
        }

    override fun onBindViewHolder(holder: CatThumbnailViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    interface AdapterCallbacks {
        fun onThumbnailClick(cat: CatMeta)
    }
}

