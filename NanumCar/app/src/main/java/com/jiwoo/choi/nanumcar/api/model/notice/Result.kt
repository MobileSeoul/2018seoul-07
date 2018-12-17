package com.jiwoo.choi.nanumcar.api.model.notice

import com.google.gson.annotations.SerializedName

data class Result(
        @SerializedName("id") val id: Int,
        @SerializedName("notice") val notice: String,
        @SerializedName("information") val information: String,
        @SerializedName("date") val date: Any?,
        @SerializedName("company") val company: String,
        @SerializedName("image") val image: String?,
        @SerializedName("link") val link: String
)