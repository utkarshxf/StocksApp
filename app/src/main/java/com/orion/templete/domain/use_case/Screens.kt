package com.orion.templete.domain.use_case

sealed class Screen(val route: String) {
    object ExploreScreen : Screen("explore_screen")
    object ProductScreen : Screen("product_screen")
}
