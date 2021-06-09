package com.rafael.data.persistance

import androidx.room.TypeConverter
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun listToJson(value: List<String>): String {
        return Gson().toJson(value)
    }
}