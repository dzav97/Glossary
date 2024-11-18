package com.example.glossaryy.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    // Menyisipkan data pengguna baru ke dalam database
    @Insert
    suspend fun insert(user: User)

    // Mendapatkan pengguna berdasarkan username dan password
    @Query("SELECT * FROM users WHERE username = :username AND password = :password LIMIT 1")
    suspend fun getUser(username: String, password: String): User?
}
