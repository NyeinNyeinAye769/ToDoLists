package com.example.todolists.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.todolists.data.MyResult
import com.example.todolists.data.local.UserDao
import com.example.todolists.data.model.User

import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao
) : UserRepositoryInterface {

    override suspend fun saveUser(user: User) {
        userDao.insert(user)
    }

    override suspend fun updateUser(user: User) {
        userDao.update(user)
    }

    override suspend fun deleteUser(userId: String) {
        TODO("Not yet implemented")
    }

    override fun getUsers(email : String): LiveData<List<User>> {
        val res = userDao.getUser(email)
        return res
    }

    override suspend fun deleteUser() {
        userDao.deleteUsers()
    }

    override fun getUserList(): LiveData<List<User>> {
        val res = userDao.getAllUsers()
        return res
    }

    override fun observeUserList(email: String): LiveData<MyResult<List<User>>> {
        return userDao.getUser(email).map {
            MyResult.Success(it)
        }
    }
}