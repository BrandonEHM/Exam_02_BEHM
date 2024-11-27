package com.example.exam_02_behm.repositories

import com.example.exam_02_behm.data.UserDao
import com.example.exam_02_behm.entities.UserEntity
import com.example.exam_02_behm.network.ClienteRetrofit
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {

    private val userService = ClienteRetrofit.userService

    suspend fun fetchApiUsers(): List<UserEntity> = userService.getAllUsers()

    fun getUsersFromDatabase(): Flow<List<UserEntity>> = userDao.getAllUsers()

    suspend fun saveUser(user: UserEntity) {
        userDao.insertUsers(listOf(user))
    }
}
