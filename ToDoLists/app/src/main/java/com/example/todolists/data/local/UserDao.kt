package com.example.todolists.data.local

import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.todolists.data.model.User

@Dao
interface UserDao {

    @Query("SELECT * FROM User")
    fun getAllUsers() : LiveData<List<User>>

    @Query("SELECT * FROM User WHERE email = :email")
    fun getUser(email : String): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(user: User)

    @Query("DELETE FROM User")
    suspend fun deleteUsers()
}