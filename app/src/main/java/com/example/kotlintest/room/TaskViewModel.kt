package com.example.kotlintest.room

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TaskViewModel(val dao:TaskDao): ViewModel() {
    var taskName= ""
    var tasks = dao.getAll()
//    var formatedTask = Transformations.map(tasks){ tasks ->
//        formatTasks(tasks)
//    }
    fun saveTask(){
        viewModelScope.launch {
            var task = Task()
            task.name = taskName
            dao.insert(task)
        }

    }

//    fun formatTasks(tasks:List<Task>):String{
//        return tasks.fold("",) {
//                str, task -> str + '\n' + formatTask(task)
//        }
//    }
//
//    fun formatTask(task:Task):String {
//        var str = "ID: ${task.id}"
//        str += '\n' +"NAME: ${task.name}"
//        str +=  '\n' + "Completed: ${task.isCompleted}"
//        return str
//    }
}