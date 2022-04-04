package com.example.pract6

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WordDao {
    @Query("SELECT * FROM word WHERE id LIKE :key LIMIT 1")
    fun getByKey(key: String): Word

    @Insert
    fun insertAll(vararg words: Word)
}