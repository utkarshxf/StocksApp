package com.orion.templete.ui.product

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.orion.templete.R
import com.orion.templete.data.model.CompanyOverviewDTO
import com.orion.templete.ui.components.StockCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(
    title: String,
    modifier: Modifier = Modifier,
    viewModel: ProductScreenViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = title) {
        viewModel.getCompanyOverview(title)
    }
    val state = viewModel.stateOfCompanyOverview.value
    when {
        state.isLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        state.error.isNotBlank() -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = state.error)
            }
        }
        state.data != null ->
        {
            Scaffold(topBar = {
                TopAppBar(
                    title = { Text(text = "PRODUCT SCREEN") },
                    modifier = Modifier.border(color = MaterialTheme.colorScheme.primary, width = 1.dp)
                )
            }, content = {
                Column(modifier = Modifier.padding(it))
                {

                    CompanyDetails(state.data)
                    AboutCompany(state.data)
                }
            })
        }
    }
}

@Composable
fun AboutCompany(companyOverview: CompanyOverviewDTO) {
    val defaultHorizontalPadding = 16.dp
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentPadding = PaddingValues(top = 16.dp, bottom = 32.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
    )
    {
        item {
            Spacer(modifier = Modifier.size(16.dp))
            SectionTitle(
                title = "Company Overview",
                modifier = Modifier.padding(defaultHorizontalPadding)
            )
        }
        item {
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
                SectionInfoItem(
                    name = "CIK",
                    value = companyOverview.CIK,
                )
                SectionInfoItem(
                    name = "Exchange",
                    value = companyOverview.Exchange
                )
                SectionInfoItem(
                    name = "Currency",
                    value = companyOverview.Currency
                )
                SectionInfoItem(
                    name = "Country",
                    value = companyOverview.Country
                )
                SectionInfoItem(
                    name = "Sector",
                    value = companyOverview.Sector
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
                    name = "Market Capitalization",
                    value = companyOverview.MarketCapitalization.toString()
                )
                SectionInfoItem(
                    name = "EBITDA",
                    value = companyOverview.EBITDA.toString()
                )
                SectionInfoItem(
                    name = "PE Ratio",
                    value = companyOverview.PERatio.toString()
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
                SectionInfoItem(
                    name = "Revenue Per Share TTM",
                    value = companyOverview.RevenuePerShareTTM.toString()
                )
                SectionInfoItem(
                    name = "Profit Margin",
                    value = companyOverview.ProfitMargin.toString()
                )
                SectionInfoItem(
                    name = "Operating Margin TTM",
                    value = companyOverview.OperatingMarginTTM.toString()
                )
                SectionInfoItem(
                    name = "Return On Assets TTM",
                    value = companyOverview.ReturnOnAssetsTTM.toString()
                )
                SectionInfoItem(
                    name = "Return On Equity TTM",
                    value = companyOverview.ReturnOnEquityTTM.toString()
                )
                SectionInfoItem(
                    name = "Revenue TTM",
                    value = companyOverview.RevenueTTM.toString()
                )
                SectionInfoItem(
                    name = "Gross Profit TTM",
                    value = companyOverview.GrossProfitTTM.toString()
                )
                SectionInfoItem(
                    name = "Diluted EPS TTM",
                    value = companyOverview.DilutedEPSTTM.toString()
                )
                SectionInfoItem(
                    name = "Quarterly Earnings Growth YOY",
                    value = companyOverview.QuarterlyEarningsGrowthYOY.toString()
                )
                SectionInfoItem(
                    name = "Quarterly Revenue Growth YOY",
                    value = companyOverview.QuarterlyRevenueGrowthYOY.toString()
                )
                SectionInfoItem(
                    name = "Analyst Target Price",
                    value = companyOverview.AnalystTargetPrice.toString()
                )
                SectionInfoItem(
                    name = "Analyst Rating Strong Buy",
                    value = companyOverview.AnalystRatingStrongBuy.toString()
                )
                SectionInfoItem(
                    name = "Analyst Rating Buy",
                    value = companyOverview.AnalystRatingBuy.toString()
                )
                SectionInfoItem(
                    name = "Analyst Rating Hold",
                    value = companyOverview.AnalystRatingHold.toString()
                )
                SectionInfoItem(
                    name = "Analyst Rating Sell",
                    value = companyOverview.AnalystRatingSell.toString()
                )
                SectionInfoItem(
                    name = "Analyst Rating Strong Sell",
                    value = companyOverview.AnalystRatingStrongSell.toString()
                )
                SectionInfoItem(
                    name = "Trailing PE",
                    value = companyOverview.TrailingPE.toString()
                )
                SectionInfoItem(
                    name = "Forward PE",
                    value = companyOverview.ForwardPE.toString()
                )
                SectionInfoItem(
                    name = "Price To Sales Ratio TTM",
                    value = companyOverview.PriceToSalesRatioTTM.toString()
                )
                SectionInfoItem(
                    name = "Price To Book Ratio",
                    value = companyOverview.PriceToBookRatio.toString()
                )
                SectionInfoItem(
                    name = "EV To Revenue",
                    value = companyOverview.EVToRevenue.toString()
                )
                SectionInfoItem(
                    name = "EV To EBITDA",
                    value = companyOverview.EVToEBITDA.toString()
                )
                SectionInfoItem(
                    name = "Beta",
                    value = companyOverview.Beta.toString()
                )
                SectionInfoItem(
                    name = "52 Week High",
                    value = companyOverview.weekHigh52.toString()
                )
                SectionInfoItem(
                    name = "52 Week Low",
                    value = companyOverview.weekLow52.toString()
                )
                SectionInfoItem(
                    name = "50 Day Moving Average",
                    value = companyOverview.movingAverage200Day.toString()
                )
                SectionInfoItem(
                    name = "200 Day Moving Average",
                    value = companyOverview.movingAverage50Day.toString()
                )
                SectionInfoItem(
                    name = "Shares Outstanding",
                    value = companyOverview.SharesOutstanding.toString()
                )
                SectionInfoItem(
                    name = "Dividend Date",
                    value = companyOverview.DividendDate
                )
                SectionInfoItem(
                    name = "Ex Dividend Date",
                    value = companyOverview.ExDividendDate
                )
            }
        }

    }
}


@Composable
fun CompanyDetails(companyOverview: CompanyOverviewDTO) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically)
        {
            AssetIcon()
            TickerName(companyOverview.Name, companyOverview.Exchange)
        }
        ValueView(companyOverview.CIK.toFloat(), companyOverview.BookValue.toFloat())
    }
}


@Composable
private fun AssetIcon(iconDrawable: Int = R.drawable.app_icon) {

    Image(
        modifier = Modifier
            .size(64.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(50)),
        contentScale = ContentScale.Crop,
        painter = painterResource(iconDrawable),
        contentDescription = null
    )

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
fun ValueView(currentValue: Float = 113.02211f, total: Float = 1356f) {
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
            text = "$${total.toInt()}",
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
