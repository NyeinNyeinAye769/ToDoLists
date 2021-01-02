package com.example.todolists.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.todolists.data.MyResult
import com.example.todolists.data.local.TaskDao
import com.example.todolists.data.model.Task
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val taskDao: TaskDao
) : TaskRepositoryInterface {

    override suspend fun saveTask(task: Task) {
        taskDao.insert(task)
    }

    override suspend fun updateTask(task: Task) {
        taskDao.update(task)
    }

    override suspend fun deleteTask(taskId: String) {
        TODO("Not yet implemented")
    }

    override fun getAllTaskList(): LiveData<List<Task>> {
        val res = taskDao.getAllTasks()
        return  res
    }

    override fun observeTaskList(): LiveData<MyResult<List<Task>>> {
        return taskDao.getAllTasks().map {
            MyResult.Success(it)
        }
    }

}