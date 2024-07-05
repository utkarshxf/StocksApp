package com.orion.templete.domain.repository

import com.orion.templete.data.model.CompanyOverviewDTO
import com.orion.templete.data.model.StockDataDTO
import com.orion.templete.data.model.TopGainLoseDTO

interface StocksRepository {
    suspend fun getTopGainerLoser(): TopGainLoseDTO
    suspend fun companyOverview(title: String): CompanyOverviewDTO
    suspend fun getStockData(): StockDataDTO
}