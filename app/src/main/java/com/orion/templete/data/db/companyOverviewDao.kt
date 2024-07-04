package com.orion.templete.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.orion.templete.data.model.CompanyOverviewDTO
import com.orion.templete.data.model.TopGainLoseDTO

@Dao
interface companyOverviewDao {
    @Insert
    suspend fun addCompanyOverview(companyOverviewDTO:CompanyOverviewDTO)

    @Query("SELECT * FROM company_overview_db")
    suspend fun getCompanyOverview(): CompanyOverviewDTO
}