package com.hamidreza.maptask.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MapEntity::class],version = 1)
abstract class MapDataBase : RoomDatabase() {
    abstract fun getMapDao() : MapDao

    companion object {
        val DATABASE_NAME = "map_db.db"
    }
}