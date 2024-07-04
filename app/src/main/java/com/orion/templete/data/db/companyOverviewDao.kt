package com.orion.templete.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.orion.templete.data.model.CompanyOverviewDTO
import com.orion.templete.data.model.TopGainLoseDTO

@Dao
interface companyOverviewDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCompanyOverview(companyOverviewDTO:CompanyOverviewDTO)

    @Query("SELECT * FROM company_overview_db WHERE objectId = :id LIMIT 1")
    suspend fun getCompanyOverview(id: Int = 1): CompanyOverviewDTO?
}