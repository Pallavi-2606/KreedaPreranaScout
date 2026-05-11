package com.example.kreedaprernascout

import androidx.room.*

@Dao
interface StudentDao {

    @Insert
    fun insert(student: Student): Long

    @Update
    fun update(student: Student)

    @Delete
    fun delete(student: Student)

    @Query("SELECT * FROM Student WHERE name LIKE '%' || :query || '%' COLLATE NOCASE")
    fun search(query: String): List<Student>

    @Query("SELECT * FROM Student")
    fun getAll(): List<Student>

    @Query("SELECT * FROM Student WHERE sport = :sport")
    fun filterBySport(sport: String): List<Student>
}