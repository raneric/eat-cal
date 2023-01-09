package com.sgg.healthykaly.adapter

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sgg.healthykaly.model.Recipe

/**
 * Binding function for imageView databinding that bind from URL using coil library
 */
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView,
              imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri()
                .buildUpon()
                .scheme("https")
                .build()
        imgView.load(imgUri)
    }
}

/**
 * binding function for recipe list recyclerView data binding
 */
@BindingAdapter("recipeList")
fun bindRecyclerView(recyclerView: RecyclerView,
                     recipeList: List<Recipe>?) {
    val adapter = recyclerView.adapter as FindListAdapter
    adapter.submitList(recipeList)
}
