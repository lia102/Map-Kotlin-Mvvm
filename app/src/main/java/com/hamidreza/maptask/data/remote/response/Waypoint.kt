package com.hamidreza.maptask.data.remote.response


import com.google.gson.annotations.SerializedName

data class Waypoint(
    @SerializedName("distance")
    val distance: Double,
    @SerializedName("hint")
    val hint: String,
    @SerializedName("location")
    val location: List<Double>,
    @SerializedName("name")
    val name: String
)