package com.jiwoo.choi.nanumcar.api.model.theme

import com.google.gson.annotations.SerializedName

data class Result(
        @SerializedName("id") val id: Int?,
        @SerializedName("number") val number: Int?,
        @SerializedName("dc_name") val dcName: String?,
        @SerializedName("lat") val lat: Double?,
        @SerializedName("lon") val lon: Double?,
        @SerializedName("image") val image: String?,
        @SerializedName("information") val information: String?,
        @SerializedName("location") val location: String?,
        @SerializedName("title") val title: String?
)