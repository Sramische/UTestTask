package com.inspirationdriven.uhomework.ui.main.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.uhomework.databinding.ListItemCatThumbnailBinding
import com.inspirationdriven.uhomework.misc.resolveCatUrl
import com.inspirationdriven.uhomework.model.CatMeta

class CatThumbnailViewHolder(
    private val binding: ListItemCatThumbnailBinding,
    private val clickListener: (CatMeta) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(cat: CatMeta) {
        binding.root.setOnClickListener {
            clickListener(cat)
        }
        val imgView = binding.imgPhoto
        with(Glide.with(imgView)) {
            clear(imgView)
            load(resolveCatUrl(cat.id)).centerCrop().into(imgView)
        }
    }
}