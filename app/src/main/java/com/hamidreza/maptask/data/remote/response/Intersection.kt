package com.hamidreza.maptask.data.remote.response


import com.google.gson.annotations.SerializedName

data class Intersection(
    @SerializedName("bearings")
    val bearings: List<Int>,
    @SerializedName("entry")
    val entry: List<Boolean>,
    @SerializedName("in")
    val inX: Int,
    @SerializedName("location")
    val location: List<Double>,
    @SerializedName("out")
    val `out`: Int
)