package com.orion.templete.data.network

import com.orion.templete.data.model.CompanyOverviewDTO
import com.orion.templete.data.model.StockDataDTO
import com.orion.templete.data.model.TopGainLoseDTO
import retrofit2.http.GET
import retrofit2.http.Query


//https://www.alphavantage.co/query?function=TOP_GAINERS_LOSERS&apikey=demo
interface ApiService {
    @GET("query?function=TOP_GAINERS_LOSERS")
    suspend fun getCompanyInfo(
        @Query("apikey") apiKey: String = "demo"
    ):retrofit2.Response<TopGainLoseDTO>

    @GET("query?function=OVERVIEW")
    suspend fun getCompanyOverview(
        @Query("symbol") ticker: String = "DECAW",
        @Query("apikey") apiKey: String = "demo"
    ):retrofit2.Response<CompanyOverviewDTO>

    //StockDataDTO
//    https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=IBM&interval=5min&apikey=demo
    @GET("query")
    suspend fun getStockData(
        @Query("function") function: String,
        @Query("symbol") symbol: String,
        @Query("interval") interval: String? = null,
        @Query("apikey") apiKey: String
    ):retrofit2.Response<StockDataDTO>



    companion object {
        const val API_KEY = "4DSA5UJD8HL9NWH0"
        const val BASE_URL = "https://alphavantage.co"
    }
}