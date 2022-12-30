package com.sgg.eatcal.adapter

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sgg.eatcal.R
import com.sgg.eatcal.model.Recipe

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

@BindingAdapter("recipeList")
fun bindRecyclerView(recyclerView: RecyclerView,
                     recipeList: List<Recipe>?) {
    val adapter = recyclerView.adapter as FindListAdapter
    adapter.submitList(recipeList)
}
