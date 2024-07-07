package com.orion.templete.ui.explore

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orion.newsapp.util.StateHandle
import com.orion.templete.domain.use_case.TickerSearchUseCase
import com.orion.templete.domain.use_case.TopGainerLoserUseCase
import com.orion.templete.ui.explore.components.ExploreScreenState
import com.orion.templete.ui.explore.components.SearchItemsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ExploreScreenViewModel @Inject constructor(
    private val topGainerLoserUseCase: TopGainerLoserUseCase,
    private val searchTopGainerLoserUseCase: TickerSearchUseCase
) : ViewModel() {

    val stateOfTopGainersLosers = mutableStateOf(ExploreScreenState())
    val searchedItems = mutableStateOf(SearchItemsState())

    init {
        getTopGainerLoser()
    }

    private fun getTopGainerLoser() {
        topGainerLoserUseCase().onEach { result ->
            when (result) {
                is StateHandle.Loading -> {
                    stateOfTopGainersLosers.value = ExploreScreenState(isLoading = true)
                }

                is StateHandle.Success -> {
                    stateOfTopGainersLosers.value = ExploreScreenState(data = result.data)
                }

                is StateHandle.Error -> {
                    stateOfTopGainersLosers.value =
                        ExploreScreenState(error = result.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }

    fun searchStock(ticket: String) {
        searchTopGainerLoserUseCase(ticket).onEach { result ->
            when (result) {
                is StateHandle.Loading -> {
                    searchedItems.value = SearchItemsState(isLoading = true)
                }

                is StateHandle.Success -> {
                    searchedItems.value = SearchItemsState(data = result.data)
                }

                is StateHandle.Error -> {
                    searchedItems.value = SearchItemsState(error = result.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }
}
