package com.orion.templete.domain.use_case

import android.content.Context
import android.util.Log
import com.orion.newsapp.util.StateHandle
import com.orion.templete.data.db.topGainLoseDatabase
import com.orion.templete.data.model.StockDataDTO
import com.orion.templete.data.model.TopGainLoseDTO
import com.orion.templete.domain.repository.StocksRepository
import com.orion.templete.util.NetworkCheck
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class StockDataUseCase @Inject constructor(
    private val repository: StocksRepository,
    private val context: Context
) {
    operator fun invoke(): Flow<StateHandle<StockDataDTO>> = flow {

        emit(StateHandle.Loading(null))
        try {
            val stockData = repository.getStockData()
            emit(StateHandle.Success(stockData))
        } catch (e: Exception) {
            emit(StateHandle.Error(e.message))
        }
    }
}