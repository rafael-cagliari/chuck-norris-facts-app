package com.rafael.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.rafael.domain.model.ChuckNorrisFact
import com.rafael.domain.model.FactCategories

class ChuckNorrisResponse {

    @SerializedName("categories")
    @Expose
    var categories: List<Categories>? = null

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("icon_url")
    @Expose
    var iconUrl: String? = null

    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null

    @SerializedName("url")
    @Expose
    var url: String? = null

    @SerializedName("value")
    @Expose
    var value: String? = null


    fun mapToDomain() = ChuckNorrisFact(
        categories = categories?.mapTo(destination = arrayListOf()){categories -> mapToCategory(categories) },
        createdAt = createdAt,
        iconUrl = iconUrl,
        id = id,
        updatedAt = updatedAt,
        url = url,
        value = value
    )

    private fun mapToCategory(categoryItem :Categories) = FactCategories(categories = categoryItem.categories)

    }

