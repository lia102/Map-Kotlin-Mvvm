package com.hamidreza.maptask.data.remote.response


import com.google.gson.annotations.SerializedName
import com.hamidreza.maptask.data.remote.response.Leg

data class Route(
    @SerializedName("distance")
    val distance: Double,
    @SerializedName("duration")
    val duration: Double,
    @SerializedName("geometry")
    val geometry: String,
    @SerializedName("legs")
    val legs: List<Leg>,
    @SerializedName("weight")
    val weight: Double,
    @SerializedName("weight_name")
    val weightName: String
)