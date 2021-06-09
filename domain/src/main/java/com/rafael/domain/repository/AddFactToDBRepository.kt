package com.rafael.domain.repository

import android.content.Context
import com.rafael.domain.model.ChuckNorrisFact

interface AddFactToDBRepository {
    fun addFactToDB(fact:ChuckNorrisFact){
    }
}