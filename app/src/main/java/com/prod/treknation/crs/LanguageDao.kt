package com.prod.treknation.crs

import androidx.room.*

@Dao
interface LanguageDao {
    @Query("SELECT * FROM Language")
    fun getAll(): List<Language>

    @Query("SELECT * FROM Language WHERE LanguageName =:language AND TYPE LIKE :id ")
    fun findByID(id: String, language: String): Language

    @Query("SELECT * FROM Language WHERE TYPE LIKE :id ")
    fun findByID(id: String): Language

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg todo: Language?)

    @Delete
    fun delete(todo: Language)

    @Query("DELETE FROM Language")
    fun delete()

    @Update
    fun updateTodo(vararg todos: Language)
}