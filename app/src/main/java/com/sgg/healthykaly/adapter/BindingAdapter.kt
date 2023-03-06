package com.sgg.healthykaly.adapter

import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.paging.PagingData
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sgg.healthykaly.R
import com.sgg.healthykaly.model.Recipe
import com.sgg.healthykaly.utils.LoadingState
import com.sgg.healthykaly.widget.CustomErrorWidget
import kotlinx.coroutines.flow.Flow

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
 * handle refresh button click listener
 */
@BindingAdapter("onRefresh")
fun bindingErrorRefreshClick(customError: CustomErrorWidget,
                             refreshListener: CustomErrorWidget.RefreshClickListener) {
    val refreshButton = customError.findViewById<Button>(R.id.refreshButton)
    refreshButton.setOnClickListener { refreshListener.refresh() }
}