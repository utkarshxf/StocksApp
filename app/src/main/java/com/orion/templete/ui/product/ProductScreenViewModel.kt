package com.orion.templete.ui.product

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orion.newsapp.util.StateHandle
import com.orion.templete.domain.use_case.CompanyOverviewUseCase
import com.orion.templete.domain.use_case.StockDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ProductScreenViewModel @Inject constructor(private val companyOverviewUseCase: CompanyOverviewUseCase , private val stockDataUseCase: StockDataUseCase) : ViewModel() {
    val stateOfCompanyOverview = mutableStateOf(ProductScreenState())
    val stateOfStockData = mutableStateOf(StockDataScreenState())
    init {
        getStockData()
    }
     fun getCompanyOverview(title: String) {
         companyOverviewUseCase(title).onEach {
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
    fun getStockData() {
        stockDataUseCase().onEach {
            when(it){
                is StateHandle.Loading -> {
                    stateOfStockData.value = StockDataScreenState(isLoading = true)
                }
                is StateHandle.Success -> {
                    stateOfStockData.value = StockDataScreenState(data = it.data)
                }
                is StateHandle.Error ->{
                    stateOfStockData.value = StockDataScreenState(error = it.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }
}