package tk.mohithaiyappa.coffeepricesm3.ui.home

import android.graphics.PointF
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BoxScope.MiniGraphView(
    modifier: Modifier,
    graphColor: Color,
    graphData: List<Float>,
) {
    val points = mutableListOf<PointF>()
    val c1 = mutableListOf<PointF>()
    val c2 = mutableListOf<PointF>()
    val max = graphData.maxOrNull() ?: Float.MAX_VALUE

    Canvas(modifier = modifier.matchParentSize()) {
        val spacer = size.width / (graphData.size - 1)
        val yAdjuster = size.height / max

        // add points x,y
        graphData.forEachIndexed { i, v ->
            points.add(PointF(i * spacer, size.height - (v * yAdjuster)))
        }

        // add berzier points
        for (i in 1 until graphData.size) {
            c1.add(PointF((points[i].x + points[i - 1].x) / 2, points[i - 1].y))
            c2.add(PointF(((points[i].x + points[i - 1].x) / 2), points[i].y))
        }

        val path = Path()
        val borderPath = Path()
        path.moveTo(points.first().x, points.first().y)
        borderPath.moveTo(points.first().x, points.first().y)
        for (i in 1 until graphData.size) {
            path.cubicTo(
                c1[i - 1].x,
                c1[i - 1].y,
                c2[i - 1].x,
                c2[i - 1].y,
                points[i].x,
                points[i].y,
            )
            borderPath.cubicTo(
                c1[i - 1].x,
                c1[i - 1].y,
                c2[i - 1].x,
                c2[i - 1].y,
                points[i].x,
                points[i].y,
            )
        }

        path.lineTo(size.width, size.height)
        path.lineTo(0f, size.height)
        drawPath(
            path = borderPath,
            color = Color(0x99543310),
            style =
                Stroke(
                    width = 7f,
                    cap = StrokeCap.Square,
                ),
        )

        drawPath(
            path = path,
            brush =
                Brush.verticalGradient(
                    listOf(graphColor.copy(alpha = 0.2f), graphColor.copy(alpha = 0f)),
                    startY = size.height - size.height * 0.8f,
                ),
            style = androidx.compose.ui.graphics.drawscope.Fill,
        )
    }
}

@Preview
@Composable
fun MiniGraphViewPreview() {
    Box(
        modifier =
            Modifier
                .height(100.dp)
                .width(200.dp),
    ) {
        MiniGraphView(
            modifier = Modifier,
            graphColor = Color(0X99543310),
            graphData =
                listOf(
                    10f,
                    20f,
                    5f,
                    17f,
                    4f,
                    14f,
                    15f,
                    0f,
                ),
        )
    }
}
