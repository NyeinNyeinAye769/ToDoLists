package com.example.todolists.ui.EditTasks

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolists.data.model.Task
import com.example.todolists.repository.TaskRepository
import kotlinx.coroutines.launch

public class EditTaskFragmentViewModel @ViewModelInject constructor(private val taskRepository: TaskRepository) : ViewModel() {

    fun updateTask(task: Task) {
        viewModelScope.launch {
            taskRepository.updateTask(task)
        }
    }
}