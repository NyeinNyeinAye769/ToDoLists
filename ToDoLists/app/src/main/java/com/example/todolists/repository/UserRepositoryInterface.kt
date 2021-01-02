package com.example.todolists.data.repository

import androidx.lifecycle.LiveData
import com.example.todolists.data.MyResult
import com.example.todolists.data.model.User

interface UserRepositoryInterface {

    suspend fun saveUser(user: User)

    suspend fun updateUser(user: User)

    suspend fun deleteUser(userId: String)

    fun getUsers(email : String): LiveData<List<User>>

    fun getUserList(): LiveData<List<User>>

    fun observeUserList(email: String): LiveData<MyResult<List<User>>>

//    fun observeTaskByEmail(): LiveData<Result<List<User>>>

    suspend fun deleteUser()
}