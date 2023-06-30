package com.mwaleneria.finalpro

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var name: String,
    var completed: Boolean = false
)