package com.sgg.healthykaly.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sgg.healthykaly.databinding.ReceipListItemBinding
import com.sgg.healthykaly.model.Recipe

class FindListAdapter : ListAdapter<Recipe, FindListAdapter.RecipeListViewHolder>(diffCallback) {

    class RecipeListViewHolder(private val binding: ReceipListItemBinding) :
            RecyclerView.ViewHolder(binding.root) {
        fun bind(recipe: Recipe) {
            binding.receip = recipe
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): RecipeListViewHolder {
        val binding = ReceipListItemBinding.inflate(LayoutInflater.from(parent.context),
                                                    parent,
                                                    false)
        return RecipeListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeListViewHolder,
                                  position: Int) {
        val recipe = getItem(position)
        holder.bind(recipe)
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Recipe>() {
            override fun areItemsTheSame(oldItem: Recipe,
                                         newItem: Recipe): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Recipe,
                                            newItem: Recipe): Boolean {
                return (oldItem.title == newItem.title) && (oldItem.image == newItem.image)
            }
        }
    }
}