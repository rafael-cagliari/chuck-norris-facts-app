package com.rafael.data.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.rafael.data.persistance.Converters


@Entity(tableName = "facts_table")
data class ChuckNorrisFactDBItem(
    @PrimaryKey
    @NonNull
    var id: String,
    var categories: String,
    var value: String)
