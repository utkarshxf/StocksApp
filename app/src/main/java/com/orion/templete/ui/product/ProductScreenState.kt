package com.orion.templete.ui.product

import com.orion.templete.data.model.CompanyOverviewDTO
import com.orion.templete.data.model.StockDataDTO

data class ProductScreenState(
    val isLoading: Boolean = false, val data: CompanyOverviewDTO? = null, val error: String = ""
)

data class StockDataScreenState(
    val isLoading: Boolean = false, val data: StockDataDTO? = null, val error: String = ""
)
