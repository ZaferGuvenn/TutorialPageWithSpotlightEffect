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
import android.view.View
import androidx.core.content.pm.ShortcutInfoCompat.Surface

class SpotlightView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {

    // Translucent black color for coating
    private val overlayColor =
        Color.parseColor("#B3000000")

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

        // Save canvas state
        canvas.save()

        // Path that will create the
        // Spotlight hole area
        val circlePath = Path().apply{
            addCircle(
                spotlightX,
                spotlightY,
                spotlightRadius,
                Path.Direction.CCW
            )
        }

        // Different method for API 26 and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            canvas.clipOutPath(circlePath)
        } else {
            @Suppress("DEPRECATION")
            canvas.clipPath(circlePath, Region.Op.DIFFERENCE)
        }

        // Draw an overlay on the clipped area
        // (all area except the hole)
        canvas.drawColor(overlayColor)

        // Restore canvas state
        canvas.restore()
    }
}