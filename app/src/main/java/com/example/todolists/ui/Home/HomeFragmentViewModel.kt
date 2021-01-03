package com.example.todolists.ui.Home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.todolists.data.MyResult
import com.example.todolists.data.model.Task
import com.example.todolists.data.repository.UserRepository
import com.example.todolists.repository.TaskRepository

public class HomeFragmentViewModel @ViewModelInject constructor(private val taskRepository: TaskRepository) : ViewModel() {

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _taskList: LiveData<List<Task>> = taskRepository.observeTaskList().switchMap {
        switchToRequiredType(it)
    }

    val taskList: LiveData<List<Task>> = _taskList

    private fun switchToRequiredType(it: MyResult<List<Task>>): LiveData<List<Task>> {
        var res = MutableLiveData<List<Task>>()
        if (it is MyResult.Success) {
            res.value = it.data
        }
        return res
    }
}
