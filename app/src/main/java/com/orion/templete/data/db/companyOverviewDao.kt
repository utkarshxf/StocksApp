package com.orion.templete.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.orion.templete.data.model.CompanyOverviewDTO

@Dao
interface CompanyOverviewDao {
    @Query("SELECT * FROM company_overview_db WHERE symbol = :symbol LIMIT 1")
    suspend fun getCompanyOverview(symbol: String): CompanyOverviewDTO?

    @Update
    suspend fun updateCompanyOverview(companyOverview: CompanyOverviewDTO)

    @Query("DELETE FROM company_overview_db WHERE lastUpdatedDate < :expirationTime")
    suspend fun deleteOldData(expirationTime: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCompanyOverview(companyOverview: CompanyOverviewDTO)
}
