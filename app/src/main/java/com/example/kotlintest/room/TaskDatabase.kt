package com.example.kotlintest.room

import android.content.Context
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class TaskDatabase: RoomDatabase() {
    abstract val taskDao : TaskDao

    companion object{
        @Volatile
        private var INSTANCE:TaskDatabase? = null
       fun getInstance(context: Context): TaskDatabase{
           synchronized(this){
               var instace = INSTANCE
               if (INSTANCE==null){
                   instace = Room.databaseBuilder(context,TaskDatabase::class.java,"task_database").build()
               }
               return instace!!
           }
        }
    }

}