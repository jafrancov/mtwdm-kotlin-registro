package dev.alexfranco.mtw.registro.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDAO {
    @Query("select * from tblUser order by name")
    fun getAllUsers(): List<User>

    @Insert
    fun insertUser(user: User)
}