package com.orion.templete.data.model

data class TopGainLoseDTO(
    val metadata: String,
    val top_gainers: List<Gainer>,
    val top_losers: List<Gainer>
)