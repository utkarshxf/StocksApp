package com.orion.templete.data.repository

import com.orion.templete.data.model.CompanyOverviewDTO
import com.orion.templete.data.model.TopGainLoseDTO
import com.orion.templete.domain.repository.StocksRepository
import com.orion.templete.util.SafeApiRequest
import com.orion.templete.data.network.ApiService
import javax.inject.Inject

class StocksRepositoryImplementation @Inject constructor(private val apiService: ApiService) :
    StocksRepository,SafeApiRequest() {
    override suspend fun getTopGainerLoser(): TopGainLoseDTO {
        val response = safeApiRequest { apiService.getCompanyInfo() }
        return response
    }

    override suspend fun companyOverview(title: String): CompanyOverviewDTO {
        val response = safeApiRequest { apiService.getCompanyOverview(title) }
        return response
    }
}