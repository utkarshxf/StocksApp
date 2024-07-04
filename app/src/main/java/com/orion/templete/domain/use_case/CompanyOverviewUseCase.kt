package com.orion.templete.domain.use_case

import com.orion.newsapp.util.StateHandle
import com.orion.templete.data.model.CompanyOverviewDTO
import com.orion.templete.data.model.TopGainLoseDTO
import com.orion.templete.domain.repository.StocksRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CompanyOverviewUseCase@Inject constructor(private val repository: StocksRepository) {
    operator fun invoke(): Flow<StateHandle<CompanyOverviewDTO>> = flow {
        emit(StateHandle.Loading(null))
        try {
            val companyOverview = repository.companyOverview()
            emit(StateHandle.Success(companyOverview))
        }catch (e:Exception){
            emit(StateHandle.Error(e.message))
        }
    }
}