package com.talip.toodoo.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.talip.toodoo.data.repo.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SaveViewModel @Inject constructor(var tRepo : TodoRepository) : ViewModel() {

    fun save(todo_task : String, todo_date : String){
        CoroutineScope(Dispatchers.Main).launch {
            tRepo.save(todo_task,todo_date)
        }
    }
}