package com.example.todolists.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User")
data class User(
        @PrimaryKey(autoGenerate = true) var id: Int?,
        @ColumnInfo(name = "name") var name: String,
        @ColumnInfo(name = "email") val email: String,
        @ColumnInfo(name = "password") val password: String
)