package com.example.todolists.ui.user_register

import android.nfc.Tag
import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todolists.data.model.User
import com.example.todolists.data.repository.UserRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.async

public class RegistrationFragmentViewModel @ViewModelInject constructor(private val userRepository: UserRepository) : ViewModel() {

    val userList: LiveData<List<User>> = userRepository.getUserList()

    fun registerUser(user: User) {
        viewModelScope.async {
            userRepository.saveUser(user)
        }
    }
}