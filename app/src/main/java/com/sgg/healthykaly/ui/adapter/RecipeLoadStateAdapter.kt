package com.sgg.healthykaly.ui.adapter

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

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