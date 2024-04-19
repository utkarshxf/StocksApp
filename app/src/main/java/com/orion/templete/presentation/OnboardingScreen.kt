package com.orion.templete.presentation

import android.content.Context
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.orion.templete.MainActivity
import com.orion.templete.R
import com.orion.templete.domain.use_case.Screen
import kotlinx.coroutines.launch


@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen(navController: NavHostController, context: MainActivity) {
    val image = listOf(
        R.drawable.online_learning_pana_1,
        R.drawable.onbording
    )

    val descriptions = listOf(
        "Ask anything from your uploaded document.",
        "Download and share your answers.",
    )
    val pagerState = rememberPagerState(
        pageCount = image.size
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF3E2318)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            contentAlignment = Alignment.TopEnd
        ) {
            Text(text = "Skip", modifier = Modifier.clickable {})
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .wrapContentSize()
                .padding(top = 52.dp)
        ) { currentPage ->
            Column(
                modifier = Modifier
                    .padding(26.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = image[currentPage]),
                    modifier = Modifier.size(400.dp),
                    contentDescription = null
                )
                Text(
                    text = descriptions[currentPage],
                    modifier = Modifier.padding(top = 12.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
            }
        }
        PageIndicator(
            pageCount = image.size,
            currentPage = pagerState.currentPage,
            modifier = Modifier.padding(16.dp)
        )
        ButtonsSection(pagerState, navController, context)
    }


}


@Composable
fun PageIndicator(pageCount: Int, currentPage: Int, modifier: Modifier) {

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {
        repeat(pageCount) {
            IndicatorSingleDot(isSelected = it == currentPage)
        }


    }
}

@Composable
fun IndicatorSingleDot(isSelected: Boolean) {

    val width = animateDpAsState(targetValue = if (isSelected) 30.dp else 12.dp, label = "")
    Box(
        modifier = Modifier
            .padding(2.dp)
            .height(8.dp)
            .width(width.value)
            .clip(CircleShape)
            .background(if (isSelected) Color(0xFFFAF3E0) else Color(0xFFD7CBB5))
    )
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ButtonsSection(
    pagerState: PagerState,
    navController: NavHostController,
    context: MainActivity
) {

    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (pagerState.currentPage == 1) {
            OutlinedButton(
                onClick = {
                    onBoardingIsFinished(context = context)
                    navController.popBackStack()
                    navController.navigate(Screen.MainScreen.route)
                },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(38.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xff89523D)
                )
            ) {
                Text(
                    text = "Get Started",
                    modifier = Modifier.padding(horizontal = 23.dp),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )
            }
        }
    }
}


private fun onBoardingIsFinished(context: MainActivity) {
    val sharedPreferences = context.getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putBoolean("isFinished", true)
    editor.apply()

}

