package com.example.todolists.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.sql.Time
import java.util.*

@Entity(tableName = "Task")
data class Task(
                @PrimaryKey(autoGenerate = true) var id: Int?,
                @ColumnInfo(name = "title") val title: String,
                @ColumnInfo(name = "tasks") val tasks: String
) : Serializable