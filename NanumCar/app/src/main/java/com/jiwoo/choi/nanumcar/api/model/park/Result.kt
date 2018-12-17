package com.jiwoo.choi.nanumcar.api.model.park

import com.google.gson.annotations.SerializedName

data class Result(
        @SerializedName("id") val id: Int?,
        @SerializedName("park_id") val parkId: String?,
        @SerializedName("title") val title: String?,
        @SerializedName("operation") val operation: String?,
        @SerializedName("type2") val type2: String?,
        @SerializedName("location_road") val locationRoad: String?,
        @SerializedName("location_jibun") val locationJibun: String?,
        @SerializedName("quantity") val quantity: Int?,
        @SerializedName("gubun") val gubun: Int?,
        @SerializedName("bujae") val bujae: String?,
        @SerializedName("learning_date") val learningDate: String?,
        @SerializedName("start_time") val startTime: String?,
        @SerializedName("end_time") val endTime: String?,
        @SerializedName("thu_start_time") val thuStartTime: String?,
        @SerializedName("thu_end_time") val thuEndTime: String?,
        @SerializedName("hol_start_time") val holStartTime: String?,
        @SerializedName("hol_end_tiem") val holEndTiem: String?,
        @SerializedName("price_type") val priceType: String?,
        @SerializedName("default_hour") val defaultHour: Int?,
        @SerializedName("default_price") val defaultPrice: Int?,
        @SerializedName("opt_hour") val optHour: Int?,
        @SerializedName("opt_price") val optPrice: Int?,
        @SerializedName("one_day_hour") val oneDayHour: Int?,
        @SerializedName("one_day_price") val oneDayPrice: Int?,
        @SerializedName("monthly_price") val monthlyPrice: Int?,
        @SerializedName("payment") val payment: String?,
        @SerializedName("other") val other: String?,
        @SerializedName("org") val org: String?,
        @SerializedName("tel") val tel: String?,
        @SerializedName("lat") val lat: Double?,
        @SerializedName("lon") val lon: Double?,
        @SerializedName("create_date") val createDate: String?
)