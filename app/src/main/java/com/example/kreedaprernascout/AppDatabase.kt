package com.example.kreedaprernascout

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Student::class, Performance::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun studentDao(): StudentDao
    abstract fun performanceDao(): PerformanceDao
}