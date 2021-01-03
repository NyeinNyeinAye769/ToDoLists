package com.example.todolists.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todolists.data.model.Task
import com.example.todolists.data.model.User

@Database(entities = arrayOf(User::class, Task::class), version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun taskDao(): TaskDao
}