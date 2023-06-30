package com.mwaleneria.finalpro

import androidx.room.*
import androidx.room.Dao

@Dao
interface TaskDao {

    @Query("SELECT * FROM tasks")
    fun getAllTasks(): List<Task>

    @Insert
    fun insertTask(task: Task): Long

    @Update
    fun updateTask(task: Task)
    @Query("UPDATE tasks SET name = :newName WHERE id = :taskId")
    fun updateTaskName(taskId: Long, newName: String)

    @Delete
    fun deleteTask(task: Task)

}
