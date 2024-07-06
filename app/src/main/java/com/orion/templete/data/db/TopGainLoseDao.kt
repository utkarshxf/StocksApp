package com.orion.templete.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.orion.templete.data.model.TopGainLoseDTO

@Dao
interface TopGainLoseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTopGainLose(topGainLoseDTO: TopGainLoseDTO)

    @Query("DELETE FROM top_gain_lose WHERE lastUpdatedDate < :expirationTime")
    suspend fun deleteOldData(expirationTime: Long)

    @Update
    suspend fun updateTopGainLose(topGainLoseDTO: TopGainLoseDTO)

    @Query("SELECT * FROM top_gain_lose")
    suspend fun getTopGainLose(): TopGainLoseDTO
}