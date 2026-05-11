package com.example.kreedaprernascout

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Performance(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val studentId: Int,
    val testType: String,   // sprint / jump
    val value: Double,      // time or distance
    val date: Long          // timestamp
)