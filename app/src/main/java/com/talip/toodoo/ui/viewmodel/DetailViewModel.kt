package com.talip.toodoo.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.talip.toodoo.data.entity.Todo
import com.talip.toodoo.data.repo.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(var tRepo : TodoRepository) : ViewModel() {

    fun update(todo_id : Int, todo_task : String, todo_date : String){
        CoroutineScope(Dispatchers.Main).launch {
            tRepo.update(todo_id,todo_task,todo_date)
        }
    }


}