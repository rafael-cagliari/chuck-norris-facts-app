package com.rafael.data.datasource

import com.rafael.data.model.ChuckNorrisFactDBItem
import com.rafael.domain.model.ChuckNorrisFact

interface AddFactToDBDataSource {
    fun addFactToDB(fact:ChuckNorrisFactDBItem)
}