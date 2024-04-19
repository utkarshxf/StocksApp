package com.orion.templete.domain.use_case

sealed class Screen(val route: String){
    object MainScreen : Screen("main_screen")
    object OnboardingScreen : Screen("onboarding_screen")
}
