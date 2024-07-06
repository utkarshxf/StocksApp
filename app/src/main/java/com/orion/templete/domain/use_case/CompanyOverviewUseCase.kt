package com.orion.templete.domain.use_case

import android.content.Context
import com.orion.newsapp.util.StateHandle
import com.orion.templete.data.db.CompanyOverviewDatabase
import com.orion.templete.data.model.CompanyOverviewDTO
import com.orion.templete.domain.repository.StocksRepository
import com.orion.templete.util.NetworkCheck
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CompanyOverviewUseCase @Inject constructor(
    private val repository: StocksRepository,
    private val companyOverviewDatabase: CompanyOverviewDatabase,
    private val context: Context
) {
    operator fun invoke(symbol: String): Flow<StateHandle<CompanyOverviewDTO>> = flow {
        emit(StateHandle.Loading(null))
        val ttl: Long = 24 * 60 * 60 * 1000 // 1 day TTL
        if (NetworkCheck.isInternetAvailable(context)) {
            try {
                val companyOverview = repository.companyOverview(symbol)
                companyOverview.lastUpdatedDate = System.currentTimeMillis()
                val currentTime = System.currentTimeMillis()
                companyOverviewDatabase.companyOverviewDao()
                    .deleteOldData(currentTime - ttl)   //delete expired data
                companyOverviewDatabase.companyOverviewDao()
                    .addCompanyOverview(companyOverview.copy(symbol = symbol))
                emit(StateHandle.Success(companyOverview))
            } catch (e: Exception) {
                emit(StateHandle.Error(e.message))
            }
        } else {
            try {
                val companyOverview =
                    companyOverviewDatabase.companyOverviewDao().getCompanyOverview(symbol)

                if (companyOverview != null && (System.currentTimeMillis() - companyOverview.lastUpdatedDate) <= ttl) {
                    emit(StateHandle.Success(companyOverview))
                } else {
                    emit(StateHandle.Error("No internet connection and no cached data found."))
                }
            } catch (e: RuntimeException) {
                emit(StateHandle.Error("API limit reached. Please try again later."))
            } catch (e: Exception) {
                emit(StateHandle.Error(e.message))
            }
        }
    }
}
