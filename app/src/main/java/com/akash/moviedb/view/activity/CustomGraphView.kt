package com.akash.moviedb.view.activity

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Shader
import android.util.AttributeSet
import android.view.View

class CustomGraphView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val dataPoints = mutableListOf<Pair<Float, Float>>()
    val paint = Paint()

    init {
        val colors = intArrayOf(Color.BLUE, Color.RED)
        val shader =
            LinearGradient(0f, 0f, 0f, height.toFloat(), colors, null, Shader.TileMode.CLAMP)

        paint.shader = shader
        paint.strokeWidth = 2f
    }

    fun setDataPoints(data: List<Pair<Float, Float>>) {
        dataPoints.clear()
        dataPoints.addAll(data)
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (canvas == null) return

        val width = width.toFloat()
        val height = height.toFloat()

        val maxCloseValue = dataPoints.maxByOrNull { it.second }?.second ?: 0f

        for ((x, close) in dataPoints) {
            val xPosition = x / dataPoints.size * width
            val yPosition = height - (close / maxCloseValue * height)

            canvas.drawLine(xPosition, height, xPosition, yPosition, paint)
        }
    }
}

