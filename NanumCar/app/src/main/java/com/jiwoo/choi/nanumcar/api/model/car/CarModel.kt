package com.jiwoo.choi.nanumcar.api.model.car

import com.google.gson.annotations.SerializedName

data class CarModel(
        @SerializedName("results") val results: List<Result>,
        @SerializedName("count") val count: Int? = null,
        @SerializedName("next") val next: Any? =null,
        @SerializedName("previous") val previous: Any? =null
)