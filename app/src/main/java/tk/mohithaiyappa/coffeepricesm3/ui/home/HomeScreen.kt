package tk.mohithaiyappa.coffeepricesm3.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.serialization.json.Json
import tk.mohithaiyappa.coffeepricesm3.data.model.Data
import tk.mohithaiyappa.coffeepricesm3.data.model.SpicePrices
import tk.mohithaiyappa.coffeepricesm3.ui.theme.CoffeePricesM3Theme
import tk.mohithaiyappa.coffeepricesm3.util.composeprev.spicePricesResponseJson
import tk.mohithaiyappa.coffeepricesm3.util.initials

@Composable
fun HomeScreen(uiState: HomeUiState) {
    when (uiState) {
        Loading -> {}
        is Error -> {
        }
        is Result<*> -> {
            val spicePrices = (uiState.data as SpicePrices).data
            LazyColumn(
                content = {
                    items(spicePrices) { data ->
                        SpiceItem(spiceData = data, modifier = Modifier.padding(8.dp))
                    }
                },
            )
        }
    }
}

@Composable
fun SpiceItem(
    spiceData: Data,
    modifier: Modifier,
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, Color(0x99EDEEEF)),
    ) {
        Row(modifier = Modifier.height(IntrinsicSize.Min)) {
            Box(
                modifier =
                    Modifier
                        .padding(16.dp)
                        .fillMaxHeight(),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    fontSize = 16.sp,
                    color = Color(0x99080300),
                    text = spiceData.spiceName.initials(),
                    modifier =
                        Modifier
                            .padding(8.dp)
                            .drawBehind {
                                drawCircle(
                                    color = Color.White,
                                    radius = this.size.maxDimension,
                                )
                            },
                )
            }

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxHeight().weight(1f),
            ) {
                Text(
                    fontSize = 16.sp,
                    text = spiceData.spiceName,
                    color = Color(0x99080300),
                    modifier =
                        Modifier
                            .padding(0.dp, 16.dp, 16.dp, 2.dp)
                            .align(Alignment.Start),
                    textAlign = TextAlign.Start,
                )
                Text(
                    fontSize = 14.sp,
//                    text = "\$120000",
                    color = Color(0x99080300),
                    text =
                        buildAnnotatedString {
                            append(spiceData.spiceCost)
                            withStyle(
                                SpanStyle(
                                    brush =
                                        Brush.linearGradient(
                                            colors = listOf(Color(0x9900B35D), Color(0x9900B35D)),
                                        ),
                                ),
                            ) {
//                                append(" (up by \$400)")
                            }
                        },
                    modifier =
                        Modifier
                            .padding(0.dp, 2.dp, 16.dp, 16.dp)
                            .align(Alignment.Start),
                    textAlign = TextAlign.Start,
                )
            }

            Box(
                modifier =
                    Modifier
                        .weight(1f)
                        .fillMaxHeight(),
            ) {
                MiniGraphView(
                    modifier = Modifier.padding(23.dp),
                    graphColor = Color(0x9954331033),
                    graphData =
                        spiceData.graphData.map { it.average.toFloat() },
                )
            }
        }
    }
}

@Preview
@Composable
fun SpiceItemPreview() {
    val spicePrices = Json.decodeFromString<SpicePrices>(spicePricesResponseJson)
    CoffeePricesM3Theme {
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    val spicePrices = Json.decodeFromString<SpicePrices>(spicePricesResponseJson)
    val homeUiState = Result(data = spicePrices)
    CoffeePricesM3Theme {
        HomeScreen(uiState = homeUiState)
    }
}
