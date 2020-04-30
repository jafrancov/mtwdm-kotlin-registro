package dev.alexfranco.mtw.registro.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.util.*

@Entity(tableName = User.TABLE_NAME)
data class User(
    @ColumnInfo(name = "name") @NotNull var name: String,
    @ColumnInfo(name = "phone") var phone: String,
    @ColumnInfo(name = "email") @NotNull var email: String? = null,
    @ColumnInfo(name = "password") @NotNull var password: String? = null,
    @ColumnInfo(name = "updated") var updated: Date
) {
    companion object {
        const val TABLE_NAME = "tblUser"
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idUser")
    var userId: Int = 0
}