package com.example.movieapp.utils

import android.graphics.LinearGradient
import android.graphics.Shader
import android.view.View
import android.widget.TextView

var lastClickTime = 0L
fun View.setOnSafeClickListener(onSafeClick: (View) -> Unit) {
    val minClickInterval = 300L // Prevent rapid clicks (300ms)

    setOnClickListener {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastClickTime >= minClickInterval) {
            lastClickTime = currentTime
            onSafeClick(it)
        }
    }
}

fun View.makeVisible() {
    this.visibility = View.VISIBLE
}

fun View.makeInVisible() {
    this.visibility = View.INVISIBLE
}

fun View.makeGone() {
    this.visibility = View.GONE
}

fun View.makeVisible(isBoolean: Boolean) {
    if (isBoolean) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}
fun TextView.applyGradient(colors: IntArray) {
    this.post {
        val gradient = LinearGradient(
            0f,
            0f,
            this.width.toFloat(),
            0f,
            colors,
            null,
            Shader.TileMode.CLAMP
        )
        this.paint.shader = gradient
        this.invalidate()
    }
}