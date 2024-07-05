package com.orion.templete.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "company_overview_db")
data class CompanyOverviewDTO(
    @PrimaryKey(autoGenerate = true)
    val objectId: Int = 0,
    @SerializedName("Symbol")
    val symbol: String,
    @SerializedName("200DayMovingAverage")
    val movingAverage200Day: String,
    @SerializedName("50DayMovingAverage")
    val movingAverage50Day: String,
    @SerializedName("52WeekHigh")
    val weekHigh52: String,
    @SerializedName("52WeekLow")
    val weekLow52: String,
    val Address: String,
    val AnalystRatingBuy: String,
    val AnalystRatingHold: String,
    val AnalystRatingSell: String,
    val AnalystRatingStrongBuy: String,
    val AnalystRatingStrongSell: String,
    val AnalystTargetPrice: String,
    val AssetType: String,
    val Beta: String,
    val BookValue: String,
    val CIK: String,
    val Country: String,
    val Currency: String,
    val Description: String,
    val DilutedEPSTTM: String,
    val DividendDate: String,
    val DividendPerShare: String,
    val DividendYield: String,
    val EBITDA: String,
    val EPS: String,
    val EVToEBITDA: String,
    val EVToRevenue: String,
    val ExDividendDate: String,
    val Exchange: String,
    val FiscalYearEnd: String,
    val ForwardPE: String,
    val GrossProfitTTM: String,
    val Industry: String,
    val LatestQuarter: String,
    val MarketCapitalization: String,
    val Name: String,
    val OperatingMarginTTM: String,
    val PEGRatio: String,
    val PERatio: String,
    val PriceToBookRatio: String,
    val PriceToSalesRatioTTM: String,
    val ProfitMargin: String,
    val QuarterlyEarningsGrowthYOY: String,
    val QuarterlyRevenueGrowthYOY: String,
    val ReturnOnAssetsTTM: String,
    val ReturnOnEquityTTM: String,
    val RevenuePerShareTTM: String,
    val RevenueTTM: String,
    val Sector: String,
    val SharesOutstanding: String,
    val TrailingPE: String,
    val createdDate: Long,
    var lastUpdatedDate: Long
)
