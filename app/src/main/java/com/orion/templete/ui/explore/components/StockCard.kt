package com.orion.templete.ui.explore.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.orion.templete.data.model.Gainer

@Composable
fun StockCard(stockCardData: Gainer, modifier: Modifier = Modifier) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    Box(
        modifier = modifier
            .size(screenWidth / 2, screenHeight / 5)
            .border(
                color = MaterialTheme.colorScheme.primary,
                width = 1.dp,
                shape = RoundedCornerShape(12.dp)
            ), contentAlignment = Alignment.CenterStart
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            AssetIcon(stockCardData.ticker)
            Spacer(modifier = Modifier.size(8.dp))
            TickerName(stockCardData.ticker)
            Spacer(modifier = Modifier.size(16.dp))
            ValueView(stockCardData.price.toFloat(), stockCardData.volume.toFloat())
        }
    }
}

@Composable
private fun AssetIcon(ticker: String?) {
    if (!ticker.isNullOrEmpty()) {
        Box(
            modifier = Modifier
                .size(52.dp)
                .clip(RoundedCornerShape(50))
                .background(MaterialTheme.colorScheme.primary), contentAlignment = Alignment.Center
        ) {
            Text(
                text = ticker.first().toString(),
                style = MaterialTheme.typography.displaySmall,
                color = MaterialTheme.colorScheme.onPrimary,
            )
        }
    }
}


@Composable
private fun TickerName(name: String = "Apple Inc.", tickerName: String = "AAPL") {
    Row(
        modifier = Modifier
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2
        )
        Text(text = tickerName, style = MaterialTheme.typography.labelSmall, color = Color.Gray)
    }
}

@Composable
fun ValueView(currentValue: Float = 113.02211f, total: Float = 1356f) {
    Column(
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = "$currentValue%",
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,

            )
        Text(
            text = total.toString(),
            style = MaterialTheme.typography.labelSmall,

            )
    }
}