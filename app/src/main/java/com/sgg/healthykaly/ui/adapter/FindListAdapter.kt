package com.sgg.healthykaly.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sgg.healthykaly.databinding.ReceipListItemBinding
import com.sgg.healthykaly.model.RecipeEntity

class FindListAdapter(private val itemClickListener: (Int) -> Unit) :
        PagingDataAdapter<RecipeEntity, FindListAdapter.RecipeListViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup,

                                    viewType: Int): RecipeListViewHolder {
        val binding = ReceipListItemBinding.inflate(LayoutInflater.from(parent.context),
                                                    parent,
                                                    false)
        val vieHolder = RecipeListViewHolder(binding)
        vieHolder.itemView.setOnClickListener {
            val recipe = getItem(vieHolder.bindingAdapterPosition)
            recipe?.let { item ->
                itemClickListener(item.id)
            }
        }
        return vieHolder
    }

    override fun onBindViewHolder(holder: RecipeListViewHolder,
                                  position: Int) {
        val recipe = getItem(position)
        recipe?.let {
            holder.bind(recipe)
        }
    }

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<RecipeEntity>() {
            override fun areItemsTheSame(oldItem: RecipeEntity,
                                         newItem: RecipeEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: RecipeEntity,
                                            newItem: RecipeEntity): Boolean {
                return (oldItem.title == newItem.title) && (oldItem.image == newItem.image)
            }
        }
    }

    class RecipeListViewHolder(private val binding: ReceipListItemBinding) :
            RecyclerView.ViewHolder(binding.root) {
        fun bind(recipeModel: RecipeEntity) {
            binding.recipe = recipeModel
            binding.executePendingBindings()
        }

    }
}