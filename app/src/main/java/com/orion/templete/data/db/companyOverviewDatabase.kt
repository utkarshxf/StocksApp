package com.orion.templete.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.orion.templete.data.model.CompanyOverviewDTO

@Database(entities = [CompanyOverviewDTO::class], version = 1)
abstract class CompanyOverviewDatabase : RoomDatabase() {
    abstract fun companyOverviewDao(): CompanyOverviewDao

    companion object {
        const val DATABASE_NAME = "company_overview_db"
    }
}