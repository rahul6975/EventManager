package com.rahul.eventmanager.model.room.entity

import androidx.room.TypeConverter

class Converters {

    @TypeConverter
    fun fromString(value: String?): List<String> {
        return value?.split(",") ?: listOf()
    }

    @TypeConverter
    fun fromList(list: List<String>?): String {
        return list?.joinToString(",") ?: ""
    }
}