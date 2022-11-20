package com.example.kotlintest.room

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "task_table")
class Task {
    @PrimaryKey(autoGenerate = true)
    var id:Long = 0L
    var name:String = ""
    var isCompleted = false

}