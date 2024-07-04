package com.orion.templete.ui.product

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orion.newsapp.util.StateHandle
import com.orion.templete.domain.use_case.CompanyOverviewUseCase
import com.orion.templete.domain.use_case.TopGainerLoserUseCase
import com.orion.templete.ui.explore.ExploreScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProductScreenViewModel @Inject constructor(private val useCase: CompanyOverviewUseCase) : ViewModel() {
    val stateOfCompanyOverview = mutableStateOf(ProductScreenState())
    init {
        getCompanyOverview()
    }
     fun getCompanyOverview() {
        useCase().onEach {
            when(it){
                is StateHandle.Loading -> {
                    stateOfCompanyOverview.value = ProductScreenState(isLoading = true)
                }
                is StateHandle.Success -> {
                    stateOfCompanyOverview.value = ProductScreenState(data = it.data)
                }
                is StateHandle.Error ->{
                    stateOfCompanyOverview.value = ProductScreenState(error = it.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }
}