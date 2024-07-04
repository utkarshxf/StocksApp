package com.orion.templete.ui.explore

import com.orion.templete.data.model.TopGainLoseDTO

data class ExploreScreenState (
    val isLoading: Boolean = false,
    val data: TopGainLoseDTO? = null,
    val error: String = ""
)