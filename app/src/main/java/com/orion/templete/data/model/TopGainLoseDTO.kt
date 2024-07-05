package com.orion.templete.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "top_gain_lose")
@TypeConverters(Converters::class)
data class TopGainLoseDTO(
    @PrimaryKey(autoGenerate = true)
    val objectId:Int,
    val metadata: String,
    val top_gainers: List<Gainer>,
    val top_losers: List<Gainer>,
    val createdDate: Long,
    var lastUpdatedDate: Long
)