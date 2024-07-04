package com.orion.templete.ui.explore

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.orion.templete.ui.components.StockCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExploreScreen(
    viewModel: ExploreScreenViewModel = hiltViewModel(),
    navigateToProductScreen: () -> Unit = {}
) {
    val state = viewModel.stateOfTopGainersLosers.value
    var TopLosers by remember { mutableStateOf(false) }
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "STOCKS APP") },
            modifier = Modifier.border(color = MaterialTheme.colorScheme.primary, width = 1.dp)
        )
    }, content = {
        when {
            state.isLoading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            state.error.isNotBlank() -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = state.error)
                }
            }
            state.data != null -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2), modifier = Modifier.padding(it),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    content = {
                        val data = if (TopLosers) state.data.top_losers else state.data.top_gainers
                        items(data)
                        {
                            Box(modifier = Modifier.clickable {
                                navigateToProductScreen()
                            })
                            {
                                StockCard(it)
                            }

                        }
                    },
                )
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter)
                {
                    Row(
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            onClick = { TopLosers = false }, modifier = Modifier
                                .weight(1f)
                                .height(52.dp), shape = RoundedCornerShape(0.dp)
                        ) {
                            Text(text = "TOP GAINERS")
                        }
                        Button(
                            onClick = { TopLosers = true }, modifier = Modifier
                                .weight(1f)
                                .height(52.dp), shape = RoundedCornerShape(0.dp)
                        ) {
                            Text(text = "TOP LOSERS")
                        }
                    }
                }

            }
        }
    })
}