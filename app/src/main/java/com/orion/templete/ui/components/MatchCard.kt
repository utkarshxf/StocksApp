package com.orion.templete.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import com.orion.templete.data.model.Match

@Composable
fun MatchCard(match: Match, modifier: Modifier = Modifier) {
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
            modifier.padding(12.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            DisplayIcon(match.name)
            Spacer(modifier = Modifier.size(8.dp))
            ItemName(match.name, match.symbol)
            Spacer(modifier = Modifier.size(16.dp))
            ValueDisplay(match.region, match.currency, match.marketOpen, match.marketClose)
        }
    }
}

@Composable
private fun DisplayIcon(word: String? = null) {
    if (!word.isNullOrEmpty()) {
        Box(
            modifier = Modifier
                .size(52.dp)
                .clip(RoundedCornerShape(50))
                .background(MaterialTheme.colorScheme.primary), contentAlignment = Alignment.Center
        ) {
            Text(
                text = word.first().toString(),
                style = MaterialTheme.typography.displaySmall,
                color = MaterialTheme.colorScheme.onPrimary,
            )
        }
    }
}

@Composable
private fun ItemName(name: String, symbol: String) {
    Row(
        modifier = Modifier
    ) {
        Text(
            text = name, style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.Bold,

            overflow = TextOverflow.Ellipsis, maxLines = 2
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(text = symbol, style = MaterialTheme.typography.labelSmall, color = Color.Gray)
    }
}

@Composable
private fun ValueDisplay(
    region: String,
    currency: String,
    marketOpen: String,
    marketClose: String
) {
    Column(
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = "Region: $region",
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,

            )
        Text(
            text = "Currency: $currency",
            style = MaterialTheme.typography.labelSmall,
            color = Color.Gray
        )
        Text(
            text = "Market Open: $marketOpen",
            style = MaterialTheme.typography.labelSmall,
            color = Color.Gray
        )
        Text(
            text = "Market Close: $marketClose",
            style = MaterialTheme.typography.labelSmall,
            color = Color.Gray
        )
    }
}