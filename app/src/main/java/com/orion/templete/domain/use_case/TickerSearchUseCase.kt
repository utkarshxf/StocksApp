package com.orion.templete.domain.use_case

import android.content.Context
import com.orion.newsapp.util.StateHandle
import com.orion.templete.R
import com.orion.templete.data.model.BestMatchesResponse
import com.orion.templete.domain.repository.StocksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TickerSearchUseCase @Inject constructor(
    private val repository: StocksRepository, private val context: Context
) {
    operator fun invoke(symbol: String): Flow<StateHandle<BestMatchesResponse>> = flow {

        emit(StateHandle.Loading(null))
        try {
            val searchedItem = repository.tickerSearch(symbol)
            emit(StateHandle.Success(searchedItem))
        } catch (e: RuntimeException) {
            emit(StateHandle.Error(context.getString(R.string.api_limit_reached_please_try_again_later)))
        } catch (e: Exception) {
            emit(StateHandle.Error(e.message))
        }
    }
}