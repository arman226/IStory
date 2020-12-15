package com.example.istory.repository

import com.example.istory.db.dao.UserDAO
import com.example.istory.db.entity.User

class UserRepository(private var dao: UserDAO) {

    val user = dao.getUser()

    suspend fun login(user: User):Long{
        return dao.login(user)
    }

    suspend fun logout(user: User):Int{
        return dao.logout(user)
    }
}