package com.hamidreza.maptask.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MapEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val longitude : Double,
    val latitude : Double
)