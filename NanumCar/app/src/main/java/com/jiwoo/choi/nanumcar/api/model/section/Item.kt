package com.jiwoo.choi.dobike.api.model.section

import com.google.gson.annotations.SerializedName

data class Item(
        @SerializedName("section") val section: String,
        @SerializedName("lat") val lat: String,
        @SerializedName("long") val long: String
)