package com.example.ambatik.ui.screen.drawing

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import com.example.ambatik.data.dataclass.drawing.Line

@Composable
fun DrawingScreen(
    modifier: Modifier = Modifier
){
    Surface(
        color = MaterialTheme.colorScheme.surface,
        modifier = modifier
            .fillMaxSize()
    ) {
        val lines = remember { mutableStateListOf<Line>() }
        var canvasWidth by remember { mutableStateOf(0f) }
        var canvasHeight by remember { mutableStateOf(0f) }
        Column {
            Box(
                modifier = modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Column {
                    Canvas(
                        modifier = modifier
                            .fillMaxWidth(0.8f)
                            .fillMaxHeight(0.8f)
                            .onGloballyPositioned { layoutCoordinates ->
                                val canvasSize = layoutCoordinates.size
                                canvasWidth = canvasSize.width.toFloat()
                                canvasHeight = canvasSize.height.toFloat()
                            }
                            .pointerInput(true) {
                                detectDragGestures { change, dragAmount ->
                                    change.consume()

                                    val startX = change.position.x - dragAmount.x
                                    val startY = change.position.y - dragAmount.y
                                    val endX = change.position.x
                                    val endY = change.position.y

                                    if (startX >= 0 && startY >= 0 &&
                                        startX <= canvasWidth && startY <= canvasHeight &&
                                        endX >= 0 && endY >= 0 &&
                                        endX <= canvasWidth && endY <= canvasHeight
                                    ) {
                                        val line = Line(
                                            start = Offset(startX, startY),
                                            end = Offset(endX, endY)
                                        )
                                        lines.add(line)
                                    }
                                }
                            }
                            .background(color = colorScheme.onPrimary)
                    ) {
                        lines.forEach { line ->
                            drawLine(
                                color = line.color,
                                start = line.start,
                                end = line.end,
                                strokeWidth = line.strokeWidth.toPx(),
                                cap = StrokeCap.Round
                            )
                        }
                    }
                    Button(
                        shape = RoundedCornerShape(10.dp),
                        onClick = {
                            lines.clear()
                        },
                        modifier = modifier
                            .padding(top = 12.dp)
                            .fillMaxWidth(0.8f)
                    ) {
                        Text("Clear")
                    }
                }
            }
        }
    }
}