package com.orion.templete.data.model

import com.google.gson.annotations.SerializedName

data class StockDataDTO(
    @SerializedName("Time Series (5min)") val timeSeries5Min: TimeSeriesMap? = null,
    @SerializedName("Time Series (Daily)") val timeSeriesDaily: TimeSeriesMap? = null,
    @SerializedName("Weekly Time Series") val timeSeriesWeekly: TimeSeriesMap? = null,
    @SerializedName("Monthly Time Series") val timeSeriesMonthly: TimeSeriesMap? = null
)
typealias TimeSeriesMap = Map<String, TimeSeriesData>