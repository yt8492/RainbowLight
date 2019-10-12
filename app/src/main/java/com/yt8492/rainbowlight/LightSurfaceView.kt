package com.yt8492.rainbowlight

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Color
import android.view.SurfaceHolder
import android.view.SurfaceView
import androidx.core.animation.addListener
import java.util.*

class LightSurfaceView(context: Context) : SurfaceView(context), SurfaceHolder.Callback {

    init {
        holder.addCallback(this)
    }

    private val colorAnimator = ValueAnimator.ofArgb(
        Color.rgb(255, 0, 0),
        Color.rgb(255, 127, 0),
        Color.rgb(255, 255, 0),
        Color.rgb(0, 255, 0),
        Color.rgb(0, 255, 255),
        Color.rgb(0, 0, 255),
        Color.rgb(139, 0, 255),
        Color.rgb(255, 0, 0)
    ).apply {
        duration = 10000
    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
        colorAnimator.addUpdateListener {
            val canvas = holder?.lockCanvas() ?: return@addUpdateListener
            val value = it.animatedValue as Int
            canvas.drawColor(value)
            holder.unlockCanvasAndPost(canvas)
        }
        colorAnimator.addListener(onEnd = {
            colorAnimator.start()
        })
        val initalValue = Date().time % 10000
        colorAnimator.currentPlayTime = initalValue
        colorAnimator.start()
    }

    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
    }
}