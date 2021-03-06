package com.example.pract6

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Word(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "value") val value: String?
)