package com.orion.templete.ui.product

import DisplayBar
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.orion.templete.R
import com.orion.templete.data.model.CompanyOverviewDTO
import com.orion.templete.data.model.StockDataDTO
import com.orion.templete.domain.use_case.DataPoint
import com.orion.templete.ui.components.LineChart
import ferg.segmented.buttons.SegmentedButtonItem
import ferg.segmented.buttons.SegmentedButtons
import kotlinx.collections.immutable.toImmutableList


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(
    title: String,
    modifier: Modifier = Modifier,
    viewModel: ProductScreenViewModel = hiltViewModel()
) {
    var selectedIndex by remember { mutableStateOf(0) }
    var function by remember { mutableStateOf("TIME_SERIES_INTRADAY") }
    var interval by remember { mutableStateOf<String?>("5min") }
    LaunchedEffect(key1 = title) {
        viewModel.getCompanyOverview(title)
    }
    LaunchedEffect(key1 = selectedIndex) {
        viewModel.getStockData(function, title, interval)
    }
    val stateOfCompanyOverview = viewModel.stateOfCompanyOverview.value
    val stateOfStockData = viewModel.stateOfStockData.value
    when {
        stateOfCompanyOverview.isLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        stateOfCompanyOverview.error.isNotBlank() -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = stateOfCompanyOverview.error)
            }
        }

        stateOfCompanyOverview.data != null -> {
            Scaffold(topBar = {
                TopAppBar(
                    title = { Text(text = "PRODUCT SCREEN") },
                    modifier = Modifier.border(
                        color = MaterialTheme.colorScheme.primary,
                        width = 1.dp
                    )
                )
            }, content = {

                LazyColumn(
                    modifier = Modifier
                        .padding(it)
                        .padding(15.dp)
                ) {
                    item {
                        CompanyDetails(stateOfCompanyOverview.data)
                    }
                    item {
                        AnimatedVisibility(
                            visible = stateOfStockData.data != null,
                            exit = ExitTransition.None
                        ) {
                            when {
                                stateOfStockData.data != null -> {
                                    val dataPoints = prepareDataPoints(stateOfStockData.data)
                                    LineChart(
                                        data = dataPoints.toImmutableList(),
                                        showDashedLine = true,
                                        showYLabels = true
                                    )
                                }
                            }
                        }
                    }
                    item {

                        SegmentedButtons {
                            SegmentedButtonItem(
                                selected = selectedIndex == 0,
                                onClick = {
                                    selectedIndex = 0
                                    function = "TIME_SERIES_INTRADAY"
                                    interval = "5min"
                                },
                                label = { Text(text = "intraday") },
                            )
                            SegmentedButtonItem(
                                selected = selectedIndex == 1,
                                onClick = {
                                    selectedIndex = 1
                                    function = "TIME_SERIES_DAILY"
                                    interval = null
                                },
                                label = { Text(text = "daywise") },
                            )
                            SegmentedButtonItem(
                                selected = selectedIndex == 2,
                                onClick = {
                                    selectedIndex = 2
                                    function = "TIME_SERIES_WEEKLY"
                                    interval = null
                                },
                                label = { Text(text = "weekly") },
                            )
                            SegmentedButtonItem(
                                selected = selectedIndex == 3,
                                onClick = {
                                    selectedIndex = 3
                                    function = "TIME_SERIES_MONTHLY"
                                    interval = null
                                },
                                label = { Text(text = "monthly") },
                            )
                        }
                    }
                    item {
                        AboutCompany(stateOfCompanyOverview.data)
                    }

                }
            })
        }
    }
}

@Composable
fun AboutCompany(companyOverview: CompanyOverviewDTO) {
    val defaultHorizontalPadding = 16.dp
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
    )
    {

        Spacer(modifier = Modifier.size(16.dp))
        SectionTitle(
            title = "Company Overview",
            modifier = Modifier.padding(defaultHorizontalPadding)
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = MaterialTheme.shapes.large
                ),
        )
        {
            Text(text = companyOverview.Description, modifier = Modifier.padding(16.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Text(
                    companyOverview.Industry, fontWeight = FontWeight.Medium, modifier = Modifier
                        .clip(
                            MaterialTheme.shapes.large
                        )
                        .background(LightGray)
                        .padding(vertical = 6.dp, horizontal = 16.dp),
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    companyOverview.Sector, fontWeight = FontWeight.Medium, modifier = Modifier
                        .clip(
                            MaterialTheme.shapes.large
                        )
                        .background(LightGray)
                        .padding(vertical = 6.dp, horizontal = 16.dp),
                    style = MaterialTheme.typography.bodySmall
                )
            }
            DisplayBar(companyOverview.weekLow52, "Avarage 50 days: "+companyOverview.movingAverage50Day,  companyOverview.weekHigh52)
            Spacer(modifier = Modifier.size(16.dp))
            SectionInfoItem(
                name = "MarketCap",
                value = companyOverview.MarketCapitalization,
            )
            SectionInfoItem(
                name = "PERatio",
                value = companyOverview.PERatio
            )
            SectionInfoItem(
                name = "Beta",
                value = companyOverview.Beta
            )
            SectionInfoItem(
                name = "Dividend Yield",
                value = companyOverview.DividendYield
            )
            SectionInfoItem(
                name = "Profit Margin",
                value = companyOverview.ProfitMargin
            )
            SectionInfoItem(
                name = "Industry",
                value = companyOverview.Industry
            )
            SectionInfoItem(
                name = "Address",
                value = companyOverview.Address
            )
            SectionInfoItem(
                name = "Fiscal Year End",
                value = companyOverview.FiscalYearEnd
            )
            SectionInfoItem(
                name = "Latest Quarter",
                value = companyOverview.LatestQuarter
            )
            SectionInfoItem(
                name = "EBITDA",
                value = companyOverview.EBITDA.toString()
            )
            SectionInfoItem(
                name = "PEG Ratio",
                value = companyOverview.PEGRatio.toString()
            )
            SectionInfoItem(
                name = "Book Value",
                value = companyOverview.BookValue.toString()
            )
            SectionInfoItem(
                name = "Dividend Per Share",
                value = companyOverview.DividendPerShare.toString()
            )
            SectionInfoItem(
                name = "Dividend Yield",
                value = companyOverview.DividendYield.toString()
            )
            SectionInfoItem(
                name = "EPS",
                value = companyOverview.EPS.toString()
            )
        }

    }
}


@Composable
fun CompanyDetails(companyOverview: CompanyOverviewDTO) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically)
        {
            AssetIcon(companyOverview.Name)
            TickerName(companyOverview.Name, companyOverview.Exchange)
        }
        ValueView(companyOverview.CIK, companyOverview.BookValue)
    }
}


@Composable
private fun AssetIcon(word: String) {

    if (!word.isNullOrEmpty()) {
        Box(
            modifier = Modifier
                .size(52.dp)
                .clip(RoundedCornerShape(50))
                .background(MaterialTheme.colorScheme.primary),
            contentAlignment = Alignment.Center
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
private fun TickerName(name: String = "Apple Inc.", tickerName: String = "AAPL") {
    Column(
        modifier = Modifier
            .padding(start = 10.dp, end = 5.dp)
            .width(80.dp)
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2
        )
        Text(text = tickerName, style = MaterialTheme.typography.labelSmall, color = Color.Gray)
    }
}

@Composable
fun ValueView(currentValue: String , total: String) {
    Column(
        modifier = Modifier
            .padding(start = 10.dp),
        horizontalAlignment = Alignment.End
    ) {
        Text(
            text = currentValue.toString(),
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Text(
            text = "$${total}",
            style = MaterialTheme.typography.labelSmall,
            color = Color.Gray
        )
    }
}

@Composable
fun SectionTitle(
    modifier: Modifier = Modifier,
    title: String
) {
    Text(
        text = title,
        modifier = modifier.semantics { heading() },
        textAlign = TextAlign.Start,
        fontWeight = FontWeight.Medium,
        style = MaterialTheme.typography.titleLarge
    )
}

@Composable
fun SectionInfoItem(
    name: String,
    value: String,
    showDivider: Boolean = true
) {
    val defaultHorizontalPadding = 16.dp
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(defaultHorizontalPadding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = name,
//            color = StocksDarkSecondaryText,
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.size(8.dp))

        Text(
            text = value,
            fontWeight = FontWeight.SemiBold,
//            color = StocksDarkPrimaryText,
            style = MaterialTheme.typography.bodyMedium
        )
    }

    if (showDivider) {
        Divider(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .alpha(.2f),
//            color = StocksDarkSecondaryText
        )
    }
}

fun prepareDataPoints(stockData: StockDataDTO): List<DataPoint> {
    val timeSeries =
        stockData.timeSeriesDaily ?: stockData.timeSeries5Min ?: stockData.timeSeriesMonthly
        ?: stockData.timeSeriesWeekly ?: return emptyList()
    return timeSeries.map { (time, entry) ->
        DataPoint(
            y = entry.close.toDouble(),
            xLabel = time,
            yLabel = entry.close
        )
    }.toList()
}