package com.example.animationincompose.presentation.bottom_navigation.screens.composables

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.yml.charts.axis.AxisData
import co.yml.charts.common.model.PlotType
import co.yml.charts.common.model.Point
import co.yml.charts.common.utils.DataUtils
import co.yml.charts.ui.barchart.models.BarChartType
import co.yml.charts.ui.linechart.LineChart
import co.yml.charts.ui.linechart.model.GridLines
import co.yml.charts.ui.linechart.model.IntersectionPoint
import co.yml.charts.ui.linechart.model.Line
import co.yml.charts.ui.linechart.model.LineChartData
import co.yml.charts.ui.linechart.model.LinePlotData
import co.yml.charts.ui.linechart.model.LineStyle
import co.yml.charts.ui.linechart.model.SelectionHighlightPoint
import co.yml.charts.ui.linechart.model.SelectionHighlightPopUp
import co.yml.charts.ui.linechart.model.ShadowUnderLine
import co.yml.charts.ui.piechart.charts.DonutPieChart
import co.yml.charts.ui.piechart.charts.PieChart
import co.yml.charts.ui.piechart.models.PieChartConfig
import co.yml.charts.ui.piechart.models.PieChartData

@Composable
fun HomeComposable() {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
//        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //-----------Line Chart---------
        Text(
            "Line Chart",
            fontSize = 32.sp
        )
        val pointsData: List<Point> =
            listOf(
                Point(0f, 0f),
                Point(1f, 90f),
                Point(2f, 0f),
                Point(3f, 60f),
                Point(4f, 10f),
                Point(5.5f, 100f)
            )
        val steps = 5
        val xAxisData = AxisData.Builder()
            .axisStepSize(100.dp)
//            .backgroundColor(Color.Blue)
            .steps(pointsData.size)
            .labelData { i -> i.toString() }
            .labelAndAxisLinePadding(15.dp)
            .axisLabelColor(Color.Black)
            .build()

        val yAxisData = AxisData.Builder()
            .steps(steps)
//            .backgroundColor()
            .labelAndAxisLinePadding(20.dp)
            .axisLabelColor(Color.White)
            .labelData { i ->
                val yScale = 100 / steps
                ((i * yScale).toString())
            }.build()

        val lineChartData = LineChartData(
            linePlotData = LinePlotData(
                lines = listOf(
                    Line(
                        dataPoints = pointsData,
                        LineStyle(),
                        IntersectionPoint(Color.Cyan),
                        SelectionHighlightPoint(),
                        ShadowUnderLine(),
                        SelectionHighlightPopUp(),

                        )
                ),
            ),
            xAxisData = xAxisData,
            yAxisData = yAxisData,
            gridLines = GridLines(),
            backgroundColor = Color.White
        )
        LineChart(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            lineChartData = lineChartData
        )

        //------------Pie Chart --------------
        Text("Pie Chart", fontSize = 32.sp)

        val pieChartData = PieChartData(
            slices = listOf(
                PieChartData.Slice("SciFi", 65f, Color(0xFF333333)),
                PieChartData.Slice("Comedy", 35f, Color(0xFF666a86)),
                PieChartData.Slice("Drama", 10f, Color(0xFF95B8D1)),
                PieChartData.Slice("Romance", 40f, Color(0xFFF53844))
            ),
            plotType = PlotType.Pie
        )
        val pieChartConfig = PieChartConfig(
            isAnimationEnable = true,
            showSliceLabels = true,
            animationDuration = 1500,
            backgroundColor = Color.Transparent,
            labelVisible = true,
            labelColor = Color.White,
            labelType = PieChartConfig.LabelType.VALUE,
        )
        PieChart(
            modifier = Modifier
                .height(400.dp),
            pieChartData,
            pieChartConfig
        )

        //---------DONUT CHART----------
        val donutChartData = PieChartData(
            slices = listOf(
                PieChartData.Slice("HP", 15f, Color(0xFF5F0A87)),
                PieChartData.Slice("Dell", 30f, Color(0xFF20BF55)),
                PieChartData.Slice("Lenovo", 40f, Color(0xFFEC9F05)),
                PieChartData.Slice("Asus", 10f, Color(0xFFF53844))
            ),
            plotType = PlotType.Donut
        )
        val donutChartConfig = PieChartConfig(
            strokeWidth = 120f,
            activeSliceAlpha = .9f,
            isAnimationEnable = true,
            backgroundColor = Color.Transparent,
            labelVisible = true,
            labelColor = Color.White,
            labelType = PieChartConfig.LabelType.PERCENTAGE,
            animationDuration = 1500,
            showSliceLabels = true,

        )
        DonutPieChart(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp),
            donutChartData,
            donutChartConfig
        )
    }
}