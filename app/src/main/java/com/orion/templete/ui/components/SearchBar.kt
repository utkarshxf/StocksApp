package com.orion.templete.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.orion.templete.R
import com.orion.templete.ui.explore.ExploreScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MySearchBar(
    navigateToProductScreen: (title: String) -> Unit,
    exploreScreenViewModel: ExploreScreenViewModel = hiltViewModel()
) {
    var text by remember {
        mutableStateOf("")
    }
    var active by remember {
        mutableStateOf(false)
    }

    var items by remember {
        mutableStateOf(mutableStateListOf<String>())
    }
    val dataState = exploreScreenViewModel.searchedItems.value
    val categories = listOf("All", "Stocks", "ETFs")
    var isSelected by remember { mutableStateOf(false) }
    var selectedCategory by remember { mutableStateOf("All") }
    val itemHistory = remember { mutableStateListOf<String>() }
    SearchBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .padding(horizontal = 12.dp),
        query = text,
        onQueryChange = {
            text = it
            items.clear()
            exploreScreenViewModel.searchStock(it)
        },
        onSearch = {
            items.add(text)
            active = false
            text = ""
        },
        active = active,
        onActiveChange = {
            exploreScreenViewModel.searchStock(text)
            active = it
        },
        placeholder = { Text(text = "Search") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(R.drawable.search_24px)
            )
        },
        trailingIcon = {
            if (active) {
                Icon(imageVector = Icons.Default.Close,
                    contentDescription = "Close Icon",
                    modifier = Modifier.clickable {
                        if (text.isNotEmpty()) {
                            text = ""
                        } else {
                            active = false
                        }
                    })
            }
        },
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
            categories.forEach { category ->

                FilterChip(selected = selectedCategory == category,
                    onClick = { selectedCategory = category
                        exploreScreenViewModel.searchStock(text + category)},
                    label = { Text(category) }, modifier = Modifier.padding(horizontal = 4.dp))
            }
        }
        itemHistory.forEach {
            Column {
                Row{
                    Text(it, modifier = Modifier.padding(vertical = 4.dp))
                    Icon(imageVector = Icons.Default.Close,
                        contentDescription = "Close Icon",
                        modifier = Modifier.clickable {
                            text = it
                            itemHistory.remove(it)
                        })
                }
            }
        }
        when {
            dataState.data?.bestMatches?.isNotEmpty() == true -> {
                LazyVerticalGrid(columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    content = {

                        items(dataState.data.bestMatches) {
                            Box(modifier = Modifier.clickable {
                                itemHistory.add(text)
                                navigateToProductScreen(it.symbol)
                            }) {
                                MatchCard(it)
                            }
                        }
                    })
            }
        }
    }
}

