package com.example.tutorialpagewithspotlighteffect

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }

    // In order to get the properties of the views correctly,
    // it is necessary to wait for them to be drawn completely.
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus){

            val imageIV = this.findViewById<ImageView>(R.id.imageView)
            val textViewTV = this.findViewById<TextView>(R.id.textView)

            val spotlightView = findViewById<SpotlightView>(R.id.spotlightView)

            // calculate the
            // center coordinates of the
            // target view to be highlighted and
            // create spotlight
            val targetView = imageIV
            val location = IntArray(2)
            targetView.getLocationInWindow(location)
            val centerX = location[0] + targetView.width / 2f
            val centerY = location[1] + targetView.height / 2f
            spotlightView.setSpotlight(centerX, centerY, targetView.width/2f)


            // To integrate it into your application,
            // you can rewrite this part in a separate
            // class to send the target view you
            // specified as a parameter.

        }
    }
}