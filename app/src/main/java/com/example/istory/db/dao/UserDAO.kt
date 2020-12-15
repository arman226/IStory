package com.example.istory.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.istory.constants.TableNames
import com.example.istory.db.entity.User


@Dao
interface UserDAO {

    @Insert
    suspend fun login(user: User):Long

    @Delete
    suspend fun logout(user:User):Int

    @Query("SELECT * FROM ${TableNames.USER_DATA_TABLE}")
    fun getUser():LiveData<User>
}