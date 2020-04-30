package dev.alexfranco.mtw.registro.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.alexfranco.mtw.registro.helpers.DateConverter

@Database(entities = arrayOf(User::class), version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class UserDB : RoomDatabase() {

    abstract fun userDao(): UserDAO

    companion object {
        private const val DATABASE_NAME = "registroApp.db"

        @Volatile
        private var INSTANCE: UserDB? = null

        fun getInstance(context: Context): UserDB? {
            INSTANCE ?: synchronized(this) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    UserDB::class.java,
                    DATABASE_NAME
                ).build()
            }
            return INSTANCE
        }
    }
}