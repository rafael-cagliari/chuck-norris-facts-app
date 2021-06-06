package com.rafael.domain.model



data class ChuckNorrisFact(
    var categories: List<FactCategories>?,
    var createdAt: String?,
    var iconUrl: String?,
    var id: String?,
    var updatedAt: String?,
    var url: String?,
    var value: String?
)