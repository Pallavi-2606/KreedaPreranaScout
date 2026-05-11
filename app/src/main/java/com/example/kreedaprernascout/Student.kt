package com.example.kreedaprernascout

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Student(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var name: String,
    var age: Int,
    var sport: String
)