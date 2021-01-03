package com.example.todolists.repository

import androidx.lifecycle.LiveData
import com.example.todolists.data.MyResult
import com.example.todolists.data.model.Task
import com.example.todolists.data.model.User

interface TaskRepositoryInterface {

    suspend fun saveTask(task: Task)

    suspend fun updateTask(task: Task)

    suspend fun deleteTask(task: Task)

    fun getAllTaskList(): LiveData<List<Task>>

    fun observeTaskList(): LiveData<MyResult<List<Task>>>

}