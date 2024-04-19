package com.orion.templete

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.orion.templete.domain.use_case.Screen
import com.orion.templete.presentation.MainScreen
import com.orion.templete.presentation.OnboardingScreen
import com.orion.templete.presentation.ui.theme.TempleteTheme
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
                    val route = if (onBoardingIsFinished(this@MainActivity)) {
                        Screen.MainScreen.route
                    } else {
                        Screen.OnboardingScreen.route
                    }
                    NavHost(
                        navController = navController,
                        startDestination = route
                    ) {
                        composable(route = Screen.MainScreen.route) {
                            MainScreen()
                        }
                        composable(route = Screen.OnboardingScreen.route) {
                            OnboardingScreen(navController , context = this@MainActivity)
                        }

                    }
                }
            }
        }
    }
}
private fun onBoardingIsFinished(context: MainActivity): Boolean {
    val sharedPreferences = context.getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
    return sharedPreferences.getBoolean("isFinished", false)

}