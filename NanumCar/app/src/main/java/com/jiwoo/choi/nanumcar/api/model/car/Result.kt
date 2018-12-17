package com.jiwoo.choi.nanumcar.api.model.car

import com.google.gson.annotations.SerializedName

data class Result(
        @SerializedName("id") val id: Int,
        @SerializedName("car_type") val carType: String,
        @SerializedName("model") val model: String,
        @SerializedName("price_per_ten_minute") val pricePerTenMinute: String?,
        @SerializedName("price_per_day") val pricePerDay: String?,
        @SerializedName("price_per_km") val pricePerKm: String,
        @SerializedName("brand") val brand: String,
        @SerializedName("image") val image: String
)