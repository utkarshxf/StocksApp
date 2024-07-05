package com.orion.templete.domain.use_case

import android.content.Context
import android.util.Log
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
        if (NetworkCheck.isInternetAvailable(context)) {
            try {
                val companyOverview = repository.companyOverview(symbol)
                companyOverviewDatabase.companyOverviewDao().addCompanyOverview(companyOverview.copy(symbol = symbol))
                emit(StateHandle.Success(companyOverview))
            } catch (e: Exception) {
                emit(StateHandle.Error(e.message))
            }
        } else {
            try {
                val companyOverview = companyOverviewDatabase.companyOverviewDao().getCompanyOverview(symbol)
                if (companyOverview != null) {
                    emit(StateHandle.Success(companyOverview))
                } else {
                    emit(StateHandle.Error("No internet connection and no cached data found."))
                }
            } catch (e: Exception) {
                emit(StateHandle.Error("No internet connection and no cached data found."))
            }
        }
    }
}
