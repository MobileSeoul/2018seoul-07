package com.jiwoo.choi.nanumcar.api.model.branch

import com.google.gson.annotations.SerializedName

data class BranchModel(
        @SerializedName("count") val count: Int,
        @SerializedName("next") val next: Any,
        @SerializedName("previous") val previous: Any,
        @SerializedName("results") val results: List<Result>
)