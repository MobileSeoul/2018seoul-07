package com.jiwoo.choi.nanumcar.api.model.branch

import com.google.gson.annotations.SerializedName

data class Result(
        @SerializedName("id") val id: Int,
        @SerializedName("title_number") val titleNumber: Int,
        @SerializedName("name") val name: String,
        @SerializedName("lat") val lat: Double,
        @SerializedName("lon") val lon: Double,
        @SerializedName("image") val image: String,
        @SerializedName("information") val information: String,
        @SerializedName("location") val location: String,
        @SerializedName("sort") val sort: Int
)