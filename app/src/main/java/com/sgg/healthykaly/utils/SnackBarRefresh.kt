package com.sgg.healthykaly.utils

import android.content.Context
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.sgg.healthykaly.R

fun showRefreshSnackBar(view: View,
                        context: Context,
                        action: () -> Unit) {
    Snackbar.make(view,
                  context.getString(R.string.error_loading_data),
                  Snackbar.LENGTH_LONG)
            .setAction(context.getString(R.string.txt_retry_button)) {
                action
            }
            .show()
}