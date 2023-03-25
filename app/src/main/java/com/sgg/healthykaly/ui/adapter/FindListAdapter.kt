package com.sgg.healthykaly.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sgg.healthykaly.databinding.ReceipListItemBinding
import com.sgg.healthykaly.model.RecipeModel

class FindListAdapter :
        PagingDataAdapter<RecipeModel, FindListAdapter.RecipeListViewHolder>(diffCallback) {

    class RecipeListViewHolder(private val binding: ReceipListItemBinding) :
            RecyclerView.ViewHolder(binding.root) {
        fun bind(recipeModel: RecipeModel) {
            binding.receip = recipeModel
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
        recipe?.let {
            holder.bind(recipe)
        }
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<RecipeModel>() {
            override fun areItemsTheSame(oldItem: RecipeModel,
                                         newItem: RecipeModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: RecipeModel,
                                            newItem: RecipeModel): Boolean {
                return (oldItem.title == newItem.title) && (oldItem.image == newItem.image)
            }
        }
    }
}