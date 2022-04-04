package com.example.pract6

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Word::class], version = 1)
abstract class WordDB : RoomDatabase() {
    abstract fun wordDao(): WordDao
}