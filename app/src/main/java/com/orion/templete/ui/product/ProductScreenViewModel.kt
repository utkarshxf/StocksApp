package com.orion.templete.ui.product

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
class ProductScreenViewModel @Inject constructor(
    private val companyOverviewUseCase: CompanyOverviewUseCase,
    private val stockDataUseCase: StockDataUseCase
) : ViewModel() {

    val stateOfCompanyOverview = mutableStateOf(ProductScreenState())
    val stateOfStockData = mutableStateOf(StockDataScreenState())

    fun getCompanyOverview(title: String) {
        companyOverviewUseCase(title).onEach { result ->
            when (result) {
                is StateHandle.Loading -> {
                    stateOfCompanyOverview.value = ProductScreenState(isLoading = true)
                }

                is StateHandle.Success -> {
                    stateOfCompanyOverview.value = ProductScreenState(data = result.data)
                }

                is StateHandle.Error -> {
                    stateOfCompanyOverview.value =
                        ProductScreenState(error = result.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getStockData(function: String, symbol: String, interval: String?) {
        stockDataUseCase(function, symbol, interval).onEach { result ->
            when (result) {
                is StateHandle.Loading -> {
                    stateOfStockData.value = StockDataScreenState(isLoading = true)
                }

                is StateHandle.Success -> {
                    stateOfStockData.value = StockDataScreenState(data = result.data)
                }

                is StateHandle.Error -> {
                    stateOfStockData.value = StockDataScreenState(error = result.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }
}
