package com.orion.templete.ui.product

import com.orion.templete.data.model.CompanyOverviewDTO
import com.orion.templete.data.model.TopGainLoseDTO

data class ProductScreenState (
    val isLoading: Boolean = false,
    val data: CompanyOverviewDTO? = null,
    val error: String = ""
)