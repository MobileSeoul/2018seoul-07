package com.jiwoo.choi.dobike.api.model.section

import com.google.gson.annotations.SerializedName

data class SectionModel(
        @SerializedName("items") val items: List<Item>
)