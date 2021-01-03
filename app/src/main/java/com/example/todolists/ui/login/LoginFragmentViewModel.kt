package com.example.todolists.ui.login

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.todolists.data.MyResult
import com.example.todolists.data.model.User
import com.example.todolists.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginFragmentViewModel @ViewModelInject constructor(private val userRepository: UserRepository) : ViewModel() {

//    lateinit var userList: LiveData<List<User>>
//
//    fun getUser(email: String) {
//        viewModelScope.async {
//            userList = userRepository.getUsers(email)
//            return@async userList
//        }
//    }
//
//     fun getUsers(email : String): LiveData<List<User>> {
//         userList = userRepository.getUsers(email)
//         return userList
//    }

    val TAG = javaClass.name

    private var _displayLoading = MutableLiveData<Boolean>(false)
    val displayLoading: LiveData<Boolean> = _displayLoading

    private var _isUserExist = MutableLiveData<Boolean>(true)
    val isUserExist: LiveData<Boolean> = _isUserExist

    private var _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

//    fun searchUserByEmail(email: String): LiveData<User> {
//        _displayLoading.value = true
//        var res = userRepository.getUsers(email)
//
//        viewModelScope.launch {
//            delay(1000)// to see progress dialog - not required when data is large amount to search
//        }
//        _displayLoading.value = false
//        return res
//    }
//
//    suspend fun searchUserByName(name: String): User? {
//        //use 'async' coroutine builder
//        _displayLoading.value = true
//        val result = viewModelScope.async(Dispatchers.IO){
//            userRepository.getUserByName(name)
//        }.await()
//
//        delay(1000)// to see progress dialog - not required when data is large amount to search
//
//        _displayLoading.value = false
//        if (result is MyResult.Success) {
//            return result.data
//        }
//        return null
//    }

    fun searchUserByEmail(email: String) {
        _displayLoading.value = true
        //use 'launch' coroutine builder
        viewModelScope.launch(Dispatchers.IO){

            delay(1000)// to see progress dialog - not required when data is large amount to search

            userRepository.getUsers(email).let { myResult ->
                if (myResult is MyResult.Success<*>)
                    userFound(myResult.data as User)
                else
                    userNotFound()

                _displayLoading.postValue(false)
            }
        }
    }

    fun userFound(data: User) {
        _user.postValue(data)
        _isUserExist.postValue(true)
    }

    fun userNotFound() {
        _isUserExist.postValue(false)
    }
}