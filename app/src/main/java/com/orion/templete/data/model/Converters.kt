package com.orion.templete.data.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromGainerList(value: List<Gainer>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Gainer>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toGainerList(value: String): List<Gainer> {
        val gson = Gson()
        val type = object : TypeToken<List<Gainer>>() {}.type
        return gson.fromJson(value, type)
    }
}