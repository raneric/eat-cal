package com.sgg.healthykaly.ui.adapter

import android.widget.Button
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import coil.load
import com.sgg.healthykaly.R
import com.sgg.healthykaly.ui.widget.CustomErrorWidget

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
                             refreshListener: CustomErrorWidget.RefreshListener) {
    val refreshButton = customError.findViewById<Button>(R.id.refreshButton)
    refreshButton.setOnClickListener { refreshListener.refresh() }
}