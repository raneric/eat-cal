package com.sgg.healthykaly.ui.adapter

import android.text.Html
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.imageLoader
import coil.request.ImageRequest
import com.sgg.healthykaly.R
import com.sgg.healthykaly.ui.widget.CustomErrorWidget

/**
 * Binding function for imageView databinding that bind from URL using coil library
 */
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView,
              imgUrl: String?) {

    val imageLoader = imgView.context.imageLoader
    val request = ImageRequest.Builder(imgView.context)
            .data(imgUrl)
            .placeholder(R.drawable.default_image)
            .error(R.drawable.default_image)
            .target(imgView)
            .build()
    imageLoader.enqueue(request)
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

@BindingAdapter("htmlText")
fun bindTextWithHtmlTag(textView: TextView,
                        text: String?) {
    text?.let {
        textView.text = Html.fromHtml(it, Html.FROM_HTML_MODE_COMPACT)
    }

}