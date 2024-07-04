package com.orion.templete

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.orion.templete.domain.use_case.Screen
import com.orion.templete.ui.explore.ExploreScreen
import com.orion.templete.ui.product.ProductScreen
import com.orion.templete.ui.theme.TempleteTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            TempleteTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.ExploreScreen.route
                    ) {
                        composable(route = Screen.ExploreScreen.route) {
                            ExploreScreen()
                            {
                                navController.currentBackStackEntry?.savedStateHandle?.set(
                                    key = "data-mapped", value = it
                                )
                                navController.navigate(Screen.ProductScreen.route)

                            }
                        }
                        composable(route = Screen.ProductScreen.route) {
                            val data =
                                navController.previousBackStackEntry
                                    ?.savedStateHandle
                                    ?.get<String?>("data-mapped")
                            if (data != null) {
                                ProductScreen(data)
                            }

                        }
                    }
                }
            }
        }
    }
}
