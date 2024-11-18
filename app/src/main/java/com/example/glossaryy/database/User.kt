package com.example.glossaryy.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,  // id sebagai primary key dan auto-generate
    val username: String,
    val password: String,
    val name: String,
    val dob: String,  // Tanggal Lahir
    val gender: String,  // Jenis kelamin
    val email: String
)