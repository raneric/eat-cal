package com.sgg.healthykaly.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.sgg.healthykaly.R
import com.sgg.healthykaly.databinding.LoadStateIndicatorBinding

class LoadStateViewHolder(private val binding: LoadStateIndicatorBinding,
                          refresh: () -> Unit) :
        RecyclerView.ViewHolder(binding.root) {
    init {
        binding.footerRefreshButton.setOnClickListener { refresh.invoke() }
    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            binding.footerErrorMessage.text = loadState.error.localizedMessage
        }
        binding.footerProgressBar.isVisible = loadState is LoadState.Loading
        binding.footerRefreshButton.isVisible = loadState is LoadState.Error
        binding.footerErrorMessage.isVisible = loadState is LoadState.Error

    }

    companion object {
        fun create(parent: ViewGroup,
                   retry: () -> Unit): LoadStateViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.load_state_indicator, parent, false)
            val binding = LoadStateIndicatorBinding.bind(view)

            return LoadStateViewHolder(binding, retry)
        }
    }

}