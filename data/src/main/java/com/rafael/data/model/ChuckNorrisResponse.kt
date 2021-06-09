package com.rafael.data.model

import com.google.gson.annotations.SerializedName
import com.rafael.domain.model.ChuckNorrisFact

data class ChuckNorrisResponse(

    @SerializedName("categories")
    var categories: List<String>,

    @SerializedName("created_at")
    var createdAt: String? = null,

    @SerializedName("icon_url")
    var iconUrl: String? = null,

    @SerializedName("id")
    var id: String,

    @SerializedName("updated_at")
    var updatedAt: String? = null,

    @SerializedName("url")
    var url: String? = null,

    @SerializedName("value")
    var value: String
) {

    fun mapToDomain() = ChuckNorrisFact(
        categories = categories,
        id = id,
        value = value
    )

}

