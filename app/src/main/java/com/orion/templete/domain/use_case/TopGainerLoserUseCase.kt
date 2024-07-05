package com.orion.templete.domain.use_case

import android.content.Context
import com.orion.newsapp.util.StateHandle
import com.orion.templete.data.db.topGainLoseDatabase
import com.orion.templete.data.model.TopGainLoseDTO
import com.orion.templete.domain.repository.StocksRepository
import com.orion.templete.util.NetworkCheck
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TopGainerLoserUseCase @Inject constructor(
    private val repository: StocksRepository,
    private val topGainLoseDatabase: topGainLoseDatabase,
    private val context: Context
) {
    operator fun invoke(): Flow<StateHandle<TopGainLoseDTO>> = flow {
        emit(StateHandle.Loading(null))
        val ttl: Long = 24 * 60 * 60 * 1000 // 1 day TTL
        if (NetworkCheck.isInternetAvailable(context)) {
            try {
                val topGainerLoser = repository.getTopGainerLoser()
                val currentTime = System.currentTimeMillis()
                topGainerLoser.lastUpdatedDate = System.currentTimeMillis()
                topGainLoseDatabase.topGainLoseDao().deleteOldData(currentTime - ttl)   //delete expired data
                topGainLoseDatabase.topGainLoseDao().addTopGainLose(topGainerLoser)
                emit(StateHandle.Success(topGainerLoser))
            } catch (e: Exception) {
                emit(StateHandle.Error(e.message))
            }
        } else {
            val topGainerLoser = topGainLoseDatabase.topGainLoseDao().getTopGainLose()
            if (topGainerLoser != null && (System.currentTimeMillis() - topGainerLoser.lastUpdatedDate) <= ttl) {
                emit(StateHandle.Success(topGainerLoser))
            } else {
                emit(StateHandle.Error("No internet connection and no cached data found."))
            }
            emit(StateHandle.Success(topGainerLoser))
        }
    }
}