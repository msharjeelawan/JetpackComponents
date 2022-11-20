package com.example.kotlintest.room

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class EditTaskViewModel(id:Long,val dao: TaskDao):ViewModel() {
    val task = dao.get(id)
    private var _navigate:MutableLiveData<Boolean> = MutableLiveData(false)
    val navigate get() = _navigate

    fun updateTask(){
        viewModelScope.launch {
            dao.update(task.value!!)
            willDoNavigation()
        }
    }

    fun deleteTask(){
        viewModelScope.launch {
            dao.delete(task.value!!)
            willDoNavigation()
        }
    }

    fun navigationDone(){
        _navigate.value = false
    }

    fun willDoNavigation(){
        _navigate.value = true
    }
}