package com.orion.templete.data.network

import com.orion.templete.data.model.CompanyOverviewDTO
import com.orion.templete.data.model.TopGainLoseDTO
import retrofit2.http.GET
import retrofit2.http.Query


//https://www.alphavantage.co/query?function=TOP_GAINERS_LOSERS&apikey=demo
interface ApiService {
    @GET("query?function=TOP_GAINERS_LOSERS")
    suspend fun getCompanyInfo(
        @Query("apikey") apiKey: String = API_KEY
    ):retrofit2.Response<TopGainLoseDTO>

    @GET("query?function=OVERVIEW")
    suspend fun getCompanyOverview(
        @Query("symbol") ticker: String = "IBM",
        @Query("apikey") apiKey: String = API_KEY
    ):retrofit2.Response<CompanyOverviewDTO>

    companion object {
        const val API_KEY = "4DSA5UJD8HL9NWH0"
        const val BASE_URL = "https://alphavantage.co"
    }
}