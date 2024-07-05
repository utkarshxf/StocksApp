package com.orion.templete.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.orion.templete.data.model.CompanyOverviewDTO
import com.orion.templete.data.model.TopGainLoseDTO

@Dao
interface CompanyOverviewDao {
    @Query("SELECT * FROM company_overview_db WHERE symbol = :symbol LIMIT 1")
    suspend fun getCompanyOverview(symbol: String): CompanyOverviewDTO?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCompanyOverview(companyOverview: CompanyOverviewDTO)
}
