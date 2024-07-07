package com.orion.templete.ui.product

import DisplayBar
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.platform.LocalContext


import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.orion.templete.R
import com.orion.templete.data.model.CompanyOverviewDTO
import com.orion.templete.data.model.StockDataDTO
import com.orion.templete.domain.use_case.DataPoint
import com.orion.templete.ui.product.components.LineChart
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
    val context = LocalContext.current
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
                    title = { Text(text = stringResource(R.string.product_screen)) },
                    modifier = Modifier.border(
                        color = MaterialTheme.colorScheme.primary, width = 1.dp
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
                            visible = stateOfStockData.data != null || stateOfStockData.error.isNotBlank()
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
                                stateOfStockData.error.isNotBlank() -> {
                                    Text(
                                        modifier = Modifier
                                            .padding(16.dp),
                                        text = stringResource(R.string.unable_to_fatch_data_try_again_later),
                                        style = MaterialTheme.typography.labelMedium,
                                        color = MaterialTheme.colorScheme.error
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
                                    function = context.getString(R.string.time_series_intraday)
                                    interval = context.getString(R.string._5min)
                                },
                                label = { Text(text = "intraday") },
                            )
                            SegmentedButtonItem(
                                selected = selectedIndex == 1,
                                onClick = {
                                    selectedIndex = 1
                                    function = context.getString(R.string.time_series_daily)
                                    interval = null
                                },
                                label = { Text(text = stringResource(R.string.daywise)) },
                            )
                            SegmentedButtonItem(
                                selected = selectedIndex == 2,
                                onClick = {
                                    selectedIndex = 2
                                    function = context.getString(R.string.time_series_weekly)
                                    interval = null
                                },
                                label = { Text(text = stringResource(R.string.weekly)) },
                            )
                            SegmentedButtonItem(
                                selected = selectedIndex == 3,
                                onClick = {
                                    selectedIndex = 3
                                    function = context.getString(R.string.time_series_monthly)
                                    interval = null
                                },
                                label = { Text(text = stringResource(R.string.monthly)) },
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
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
    ) {

        Spacer(modifier = Modifier.size(16.dp))
        SectionTitle(
            title = stringResource(R.string.company_overview),
            modifier = Modifier.padding(defaultHorizontalPadding)
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .background(
                    color = MaterialTheme.colorScheme.surface, shape = MaterialTheme.shapes.large
                ),
        ) {
            Text(text = companyOverview.Description, modifier = Modifier.padding(16.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    companyOverview.Industry,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .clip(
                            MaterialTheme.shapes.large
                        )
                        .background(LightGray)
                        .padding(vertical = 6.dp, horizontal = 16.dp),
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    companyOverview.Sector,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .clip(
                            MaterialTheme.shapes.large
                        )
                        .background(LightGray)
                        .padding(vertical = 6.dp, horizontal = 16.dp),
                    style = MaterialTheme.typography.bodySmall
                )
            }
            DisplayBar(
                companyOverview.weekLow52,
                stringResource(R.string.avarage_50_days) + companyOverview.movingAverage50Day,
                companyOverview.weekHigh52
            )
            Spacer(modifier = Modifier.size(16.dp))
            SectionInfoItem(
                name = stringResource(R.string.marketcap),
                value = companyOverview.MarketCapitalization,
            )
            SectionInfoItem(
                name = stringResource(R.string.peratio), value = companyOverview.PERatio
            )
            SectionInfoItem(
                name = stringResource(R.string.beta), value = companyOverview.Beta
            )
            SectionInfoItem(
                name = stringResource(R.string.dividend_yield),
                value = companyOverview.DividendYield
            )
            SectionInfoItem(
                name = stringResource(R.string.profit_margin), value = companyOverview.ProfitMargin
            )
            SectionInfoItem(
                name = stringResource(R.string.industry), value = companyOverview.Industry
            )
            SectionInfoItem(
                name = stringResource(R.string.address), value = companyOverview.Address
            )
            SectionInfoItem(
                name = stringResource(R.string.fiscal_year_end),
                value = companyOverview.FiscalYearEnd
            )
            SectionInfoItem(
                name = stringResource(R.string.latest_quarter),
                value = companyOverview.LatestQuarter
            )
            SectionInfoItem(
                name = stringResource(R.string.ebitda), value = companyOverview.EBITDA.toString()
            )
            SectionInfoItem(
                name = stringResource(R.string.peg_ratio),
                value = companyOverview.PEGRatio.toString()
            )
            SectionInfoItem(
                name = stringResource(R.string.book_value),
                value = companyOverview.BookValue.toString()
            )
            SectionInfoItem(
                name = stringResource(R.string.dividend_per_share),
                value = companyOverview.DividendPerShare.toString()
            )
            SectionInfoItem(
                name = stringResource(R.string.eps), value = companyOverview.EPS.toString()
            )
        }

    }
}


@Composable
fun CompanyDetails(companyOverview: CompanyOverviewDTO) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
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
            overflow = TextOverflow.Ellipsis,
            maxLines = 2
        )
        Text(text = tickerName, style = MaterialTheme.typography.labelSmall, color = Color.Gray)
    }
}

@Composable
fun ValueView(currentValue: String, total: String) {
    Column(
        modifier = Modifier.padding(start = 10.dp), horizontalAlignment = Alignment.End
    ) {
        Text(
            text = currentValue.toString(),
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Text(
            text = "$${total}", style = MaterialTheme.typography.labelSmall, color = Color.Gray
        )
    }
}

@Composable
fun SectionTitle(
    modifier: Modifier = Modifier, title: String
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
    name: String, value: String, showDivider: Boolean = true
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
            text = value, fontWeight = FontWeight.SemiBold,
//            color = StocksDarkPrimaryText,
            style = MaterialTheme.typography.bodyMedium
        )
    }

    if (showDivider) {
        HorizontalDivider(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .alpha(.2f)
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
            y = entry.close.toDouble(), xLabel = time, yLabel = entry.close
        )
    }.toList()
}