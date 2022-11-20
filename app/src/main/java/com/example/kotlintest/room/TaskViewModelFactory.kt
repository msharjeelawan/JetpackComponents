package com.example.kotlintest.room

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class TaskViewModelFactory(val dao:TaskDao):ViewModelProvider.Factory {


    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(TaskViewModel::class.java)){
            return TaskViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknow view Model")
    }
}