package com.sgg.healthykaly.ui.animation

import android.animation.ObjectAnimator
import android.view.View

class FadeInFadeOutAnimation(private val view: View) {
    fun fadeIn() {
        view.visibility = View.VISIBLE
        val animator = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f)
        animator.duration = 600
        animator.start()
    }

    fun fadeOut() {
        val animator = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f)
        animator.duration = 600
        animator.start()
        view.visibility = View.GONE
    }
}