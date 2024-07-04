package com.orion.templete.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.orion.templete.data.model.CompanyOverviewDTO
import com.orion.templete.data.model.TopGainLoseDTO

@Database(entities = [CompanyOverviewDTO::class], version = 1)
abstract class companyOverviewDatabase : RoomDatabase() {

    abstract fun companyOverviewDao(): companyOverviewDao

    companion object {
        const val DATABASE_NAME = "company_overview_db"
    }
}