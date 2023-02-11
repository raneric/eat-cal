package com.sgg.healthykaly.adapter

import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sgg.healthykaly.R
import com.sgg.healthykaly.model.Recipe
import com.sgg.healthykaly.util.LoadingState
import com.sgg.healthykaly.widget.CustomErrorWidget

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

/**
 * handle error visibility depending on load state from view model
 */
@BindingAdapter("loadingState")
fun bindingErrorVisibility(customError: CustomErrorWidget,
                           loadingState: LoadingState) {
    customError.visibility = if (loadingState == LoadingState.ERROR) View.VISIBLE else View.GONE

}

/**
 * handle refresh button click listener
 */
@BindingAdapter("onRefresh")
fun bindingErrorRefreshClick(customError: CustomErrorWidget,
                             onRefresh: () -> Unit) {
    val refresh = customError.findViewById<Button>(R.id.refreshButton)
    refresh.setOnClickListener { onRefresh.invoke() }
}