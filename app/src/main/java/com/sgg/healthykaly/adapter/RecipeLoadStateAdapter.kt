package com.sgg.healthykaly.adapter

import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sgg.healthykaly.databinding.LoadStateIndicatorBinding

class RecipeLoadStateAdapter(private val refresh: () -> Unit) :
        LoadStateAdapter<LoadStateViewHolder>() {
    override fun onBindViewHolder(holder: LoadStateViewHolder,
                                  loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    loadState: LoadState): LoadStateViewHolder {
        return LoadStateViewHolder.create(parent, refresh)
    }
}