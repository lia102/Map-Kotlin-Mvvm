package com.hamidreza.maptask.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MapDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToMap(mapEntity: MapEntity)

    @Query("SELECT * FROM MapEntity")
    fun getSymbols():LiveData<List<MapEntity>>

}