package com.orion.templete.ui.explore

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orion.newsapp.util.StateHandle
import com.orion.templete.domain.use_case.TopGainerLoserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ExploreScreenViewModel @Inject constructor(private val useCase: TopGainerLoserUseCase) : ViewModel() {
    val stateOfTopGainersLosers = mutableStateOf(ExploreScreenState())
    init {
        getTopGainerLoser()
    }
    private fun getTopGainerLoser() {
        useCase().onEach {
            when(it){
                is StateHandle.Loading -> {
                    stateOfTopGainersLosers.value = ExploreScreenState(isLoading = true)
                }
                is StateHandle.Success -> {
                    stateOfTopGainersLosers.value = ExploreScreenState(data = it.data)
                }
                is StateHandle.Error ->{
                    stateOfTopGainersLosers.value = ExploreScreenState(error = it.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }
}