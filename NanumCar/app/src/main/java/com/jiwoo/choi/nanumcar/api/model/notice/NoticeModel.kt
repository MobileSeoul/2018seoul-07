package com.jiwoo.choi.nanumcar.api.model.notice

import com.google.gson.annotations.SerializedName

data class NoticeModel(
        @SerializedName("count") val count: Int,
        @SerializedName("next") val next: Any?,
        @SerializedName("previous") val previous: Any?,
        @SerializedName("results") val results: List<Result>
)