package com.orion.templete.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.orion.templete.data.model.TopGainLoseDTO

@Dao
interface topGainLoseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTopGainLose(topGainLoseDTO: TopGainLoseDTO)

    @Query("SELECT * FROM top_gain_lose")
    suspend fun getTopGainLose(): TopGainLoseDTO
}