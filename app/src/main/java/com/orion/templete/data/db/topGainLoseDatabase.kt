package com.orion.templete.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.orion.templete.data.model.TopGainLoseDTO

@Database(entities = [TopGainLoseDTO::class], version = 1)
abstract class topGainLoseDatabase : RoomDatabase() {

    abstract fun topGainLoseDao(): topGainLoseDao

    companion object {
        const val DATABASE_NAME = "top_gain_lose_db"
    }
}