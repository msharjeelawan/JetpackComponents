package com.example.kotlintest.room

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class EditTaskModelFactory(val id: Long,val dao: TaskDao):ViewModelProvider.Factory {

    override fun <T: ViewModel> create(veiwModel: Class<T>): T{
        if (veiwModel.isAssignableFrom(EditTaskViewModel::class.java))
            return  EditTaskViewModel(id,dao) as T

        throw  IllegalArgumentException("Wrong View Model")
    }
}