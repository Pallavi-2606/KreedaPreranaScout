package com.example.kreedaprernascout

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PerformanceDao {

    @Insert
    fun insert(performance: Performance)

    @Query("SELECT * FROM Performance WHERE studentId = :id")
    fun getByStudent(id: Int): List<Performance>

    @Query("""
    SELECT student.name AS name,
    MIN(performance.value) AS time
    FROM performance
    INNER JOIN student
    ON performance.studentId = student.id
    GROUP BY student.id
    ORDER BY time ASC
    """)
    fun getLeaderboard(): List<LeaderboardItem>

    @Query("SELECT * FROM Performance WHERE studentId = :id ORDER BY date DESC LIMIT 1")
    fun getLatestByStudent(id: Int): Performance?

    @Query("SELECT * FROM performance")
    fun getAll(): List<Performance>

}