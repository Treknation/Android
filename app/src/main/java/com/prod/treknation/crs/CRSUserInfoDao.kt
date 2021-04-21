package com.prod.treknation.crs

import androidx.room.*

@Dao
interface CRSUserInfoDao {
    @Query("SELECT * FROM CRSUserInfo")
    fun getAll(): List<CRSUserInfo>

    @Query("SELECT * FROM CRSUserInfo WHERE id LIKE :id")
    fun findByID(id: String): CRSUserInfo

    @Insert
    fun insert(vararg todo: CRSUserInfo?)

    @Delete
    fun delete(todo: CRSUserInfo)

    @Update
    fun updateTodo(vararg todos: CRSUserInfo)
}