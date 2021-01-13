package com.hamidreza.maptask.data.remote.response


import com.google.gson.annotations.SerializedName
import com.hamidreza.maptask.data.remote.response.Intersection
import com.hamidreza.maptask.data.remote.response.Maneuver

data class Step(
    @SerializedName("distance")
    val distance: Double,
    @SerializedName("driving_side")
    val drivingSide: String,
    @SerializedName("duration")
    val duration: Double,
    @SerializedName("geometry")
    val geometry: String,
    @SerializedName("intersections")
    val intersections: List<Intersection>,
    @SerializedName("maneuver")
    val maneuver: Maneuver,
    @SerializedName("mode")
    val mode: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("weight")
    val weight: Double
)