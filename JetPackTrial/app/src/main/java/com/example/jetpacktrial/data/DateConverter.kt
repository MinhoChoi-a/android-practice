package com.example.jetpacktrial.data

import androidx.room.TypeConverter
import java.util.*

//SQLite cannot manage date type data, therefore it should be Long value

class DateConverter {
    @TypeConverter
    fun fromTimestamp(value: Long): Date {
        return Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date): Long {
        return date.time
    }


}