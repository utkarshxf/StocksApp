package com.orion.templete.domain.use_case

import android.content.Context
import com.orion.newsapp.util.StateHandle
import com.orion.templete.R
import com.orion.templete.data.model.StockDataDTO
import com.orion.templete.domain.repository.StocksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class StockDataUseCase @Inject constructor(
    private val repository: StocksRepository, private val context: Context
) {
    operator fun invoke(
        function: String,
        symbol: String,
        interval: String?
    ): Flow<StateHandle<StockDataDTO>> = flow {

        emit(StateHandle.Loading(null))
        try {
            val stockData =
                repository.getStockData(function = function, symbol = symbol, interval = interval)
            emit(StateHandle.Success(stockData))
        } catch (e: RuntimeException) {
            emit(StateHandle.Error(context.getString(R.string.api_limit_reached_please_try_again_later)))
        } catch (e: Exception) {
            emit(StateHandle.Error(e.message))
        }
    }
}