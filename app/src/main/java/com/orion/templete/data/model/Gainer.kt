package com.orion.templete.data.model

import androidx.room.Entity


data class Gainer(
    val change_amount: String,
    val change_percentage: String,
    val price: String,
    val ticker: String,
    val volume: String
)