package com.example.todolists.ui.DeleteTasks

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolists.data.model.Task
import com.example.todolists.repository.TaskRepository
import kotlinx.coroutines.async

public class DeleteTaskFragmentViewModel @ViewModelInject constructor(private val taskRepository: TaskRepository) : ViewModel() {

    val taskList: LiveData<List<Task>> = taskRepository.getAllTaskList()

    fun addTask(task: Task) {
        viewModelScope.async {
            taskRepository.saveTask(task)
        }
    }
}