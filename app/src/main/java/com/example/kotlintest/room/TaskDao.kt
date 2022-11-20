package com.example.kotlintest.room

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao {

    @Insert
   suspend fun insert(task:Task)

    @Update
    suspend fun update(task: Task)

    @Delete
    suspend fun delete(task: Task)

    @Query("Select * FROM TASK_TABLE where id = :taskId")
    fun get(taskId: Long):LiveData<Task>

    @Query("Select * FROM task_table ORDER BY id DESC")
    fun getAll():LiveData<List<Task>>

}