package com.lcz.cpa.compose.customview

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

/**
 *
 * desc: 五角星
 *
 * create by lcz on 2023-03-31
 */
@Composable
fun Star() {
    Canvas(modifier = Modifier.size(100.dp)) {
        val radius = size.minDimension / 2
        val outerRadius = radius * 0.8f
        val innerRadius = radius * 0.4f
        val centerX = size.width / 2
        val centerY = size.height / 2
        val points = mutableListOf<Offset>()

        for (i in 0 until 5) {
            val theta = i * 2 * PI / 5
            val x = (centerX + outerRadius * cos(theta)).toFloat()
            val y = (centerY + outerRadius * sin(theta)).toFloat()
            points.add(Offset(x, y))
            val theta2 = (i * 2 + 1) * PI / 5
            val x2 = (centerX + innerRadius * cos(theta2)).toFloat()
            val y2 = (centerY + innerRadius * sin(theta2)).toFloat()
            points.add(Offset(x2, y2))
        }

        drawPath(
            path = Path().apply {
                moveTo(points[0].x, points[0].y)
                for (i in 1 until points.size) {
                    lineTo(points[i].x, points[i].y)
                }
                close()
            },
            color = Color.Blue,
            style = Stroke(width = 5f)
        )
    }
}
