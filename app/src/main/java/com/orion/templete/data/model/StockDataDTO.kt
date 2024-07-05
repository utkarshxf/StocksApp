package com.orion.templete.data.model

import com.google.gson.annotations.SerializedName

data class StockDataDTO(
    @SerializedName("Time Series (5min)") val timeSeries5Min: TimeSeriesMap? = null,
    @SerializedName("Time Series (Daily)") val timeSeriesDaily: TimeSeriesMap? = null,
    @SerializedName("Time Series (Weekly)") val timeSeriesWeekly: TimeSeriesMap? = null,
    @SerializedName("Time Series (Monthly)") val timeSeriesMonthly: TimeSeriesMap? = null
)
typealias TimeSeriesMap = Map<String, TimeSeriesData>