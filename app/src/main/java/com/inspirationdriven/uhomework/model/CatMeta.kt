package com.inspirationdriven.uhomework.model

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.parcelize.Parcelize

@Parcelize
data class CatMeta(
    @JsonProperty("id") val id: String,
    @JsonProperty("created_at") val createdAt: String,
    @JsonProperty("tags") val tags: List<String>
) : Parcelable