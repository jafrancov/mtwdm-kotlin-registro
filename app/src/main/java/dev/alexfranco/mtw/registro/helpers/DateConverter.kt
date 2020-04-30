package dev.alexfranco.mtw.registro.helpers

import androidx.room.TypeConverter
import java.util.*

class DateConverter {
    @TypeConverter
    fun toDate(timestamp: Long?): Date? {
        return if (timestamp != null) Date(timestamp) else null
    }

    @TypeConverter
    fun toTime(date: Date): Long? = date.time
}