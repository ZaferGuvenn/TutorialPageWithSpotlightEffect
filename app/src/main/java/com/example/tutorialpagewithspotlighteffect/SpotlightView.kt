package com.example.tutorialpagewithspotlighteffect

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.util.AttributeSet
import android.view.View

class SpotlightView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {
    private val backgroundPaint = Paint().apply {
        color = Color.parseColor("#B3000000") // Yarı şeffaf siyah
        style = Paint.Style.FILL
    }

    init {
        // disable hardware acceleration
        // This method is
        // Required for
        // "PorterDuffXfermode(PorterDuff.Mode.CLEAR)"
        // to work properly
        setLayerType(LAYER_TYPE_SOFTWARE, null)
    }

    private val clearPaint = Paint().apply {
        xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
    }
    private var spotlightX = 0f
    private var spotlightY = 0f
    private var spotlightRadius = 0f

    // Set spotlight coordinates and radius
    fun setSpotlight(x: Float, y: Float, radius: Float) {
        spotlightX = x
        spotlightY = y
        spotlightRadius = radius
        invalidate() // Triggers the onDraw method again
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // Dim full screen
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), backgroundPaint)
        // Clear Spotlight region (make transparent)
        canvas.drawCircle(spotlightX, spotlightY, spotlightRadius, clearPaint)
    }
}