package com.sgg.healthykaly.widget

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.view.setPadding
import com.sgg.healthykaly.R

class CustomErrorWidget @JvmOverloads constructor(context: Context,
                                                  attrs: AttributeSet? = null,
                                                  defStyleAttr: Int = 0,
                                                  defStyleRes: Int = 0) :
        LinearLayout(context, attrs, defStyleAttr, defStyleRes) {

    init {
        orientation = LinearLayout.VERTICAL
        gravity = Gravity.CENTER
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.custom_error, this)
        setPadding(resources.getDimensionPixelSize(R.dimen.padding_error_widget))
        setBackgroundResource(R.drawable.error_background)
        visibility = GONE
    }

    interface RefreshListener {
        fun refresh()
    }
}

