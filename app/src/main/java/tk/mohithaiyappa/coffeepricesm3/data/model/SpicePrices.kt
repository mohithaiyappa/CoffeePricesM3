package tk.mohithaiyappa.coffeepricesm3.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpicePrices(
    @SerialName("data")
    val `data`: List<Data>,
    @SerialName("status")
    val status: String,
)

@Serializable
data class Data(
    @SerialName("average")
    val average: Int,
    @SerialName("graphData")
    val graphData: List<GraphData>,
    @SerialName("_id")
    val id: String,
    @SerialName("priority")
    val priority: Int,
    @SerialName("scrappedAt")
    val scrappedAt: String,
    @SerialName("spiceCost")
    val spiceCost: String,
    @SerialName("spiceName")
    val spiceName: String,
    @SerialName("status")
    val status: Int,
    @SerialName("__v")
    val v: Int,
)

@Serializable
data class GraphData(
    @SerialName("average")
    val average: Double,
    @SerialName("_id")
    val id: String,
    @SerialName("scrappedAt")
    val scrappedAt: String,
)
