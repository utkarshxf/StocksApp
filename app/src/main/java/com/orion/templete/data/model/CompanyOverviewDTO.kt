package com.orion.templete.data.model

data class CompanyOverviewDTO(
    val `200DayMovingAverage`: String,
    val `50DayMovingAverage`: String,
    val `52WeekHigh`: String,
    val `52WeekLow`: String,
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
    val Symbol: String,
    val TrailingPE: String
)