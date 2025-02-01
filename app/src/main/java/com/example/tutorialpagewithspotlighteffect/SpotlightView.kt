package com.example.tutorialpagewithspotlighteffect

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.Region
import android.os.Build
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.pm.ShortcutInfoCompat.Surface

class SpotlightView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {


    private var spotlightX = 0f
    private var spotlightY = 0f
    private var spotlightRadius = 0f

    fun setSpotlight(x: Float, y: Float, radius: Float) {
        spotlightX = x
        spotlightY = y
        spotlightRadius = radius
        invalidate() // Yeniden çizimi tetikler
    }

    private val paint = Paint().apply {
        color = Color.BLACK    // Frame color
        style = Paint.Style.FILL
        isAntiAlias = true
    }

    // Instead of recreating the path every time,
    // you can use it by creating and
    // cleaning it beforehand for performance.
    private val path = Path().apply {
        fillType = Path.FillType.EVEN_ODD
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // First, let's clear the path
        path.reset()
        path.fillType = Path.FillType.EVEN_ODD

        // Outer rectangle:
        // Entire View (or any other area you want)
        path.addRect(0f, 0f, width.toFloat(), height.toFloat(), Path.Direction.CW)

        // Inner rectangle (hole):
        path.addCircle(spotlightX, spotlightY, spotlightRadius, Path.Direction.CW)

        // Path drawing:
        // Thanks to the EVEN_ODD rule,
        // the inner rectangle remains a hole.
        canvas.drawPath(path, paint)
    }

    // For drag and drop effect
    // we can use touch events
    override fun onTouchEvent(event: MotionEvent?): Boolean {

        event?.let {
            spotlightX = it.x
            spotlightY = it.y

            when ( it.action ) {

                MotionEvent.ACTION_UP -> spotlightRadius -= 100
                MotionEvent.ACTION_DOWN -> spotlightRadius += 100
            }

            invalidate()
        }


        return true
    }
}