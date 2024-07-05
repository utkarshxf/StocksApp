package com.orion.templete.data.model

import com.google.gson.annotations.SerializedName

data class TimeSeriesData(
    @SerializedName("1. open") val open: String,
    @SerializedName("2. high") val high: String,
    @SerializedName("3. low") val low: String,
    @SerializedName("4. close") val close: String,
    @SerializedName("5. volume") val volume: String,
    @SerializedName("5. adjusted close") val adjustedClose: String? = null,
    @SerializedName("7. dividend amount") val dividendAmount: String? = null,
    @SerializedName("8. split coefficient") val splitCoefficient: String? = null
)