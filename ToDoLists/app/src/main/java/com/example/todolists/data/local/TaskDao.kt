package com.example.todolists.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.todolists.data.model.Task
import com.example.todolists.data.model.User

@Dao
interface TaskDao {
    @Query("SELECT * FROM Task WHERE tasks = :tasks")
    fun getTask(tasks: String): LiveData<Task>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: Task)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(task: Task)

    @Query("SELECT * FROM Task")
    fun getAllTasks():  LiveData<List<Task>>
}