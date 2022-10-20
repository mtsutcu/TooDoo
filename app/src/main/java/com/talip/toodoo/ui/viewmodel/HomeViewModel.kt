package com.talip.toodoo.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.talip.toodoo.data.entity.Todo
import com.talip.toodoo.data.repo.TodoRepository
import com.talip.toodoo.ui.adapter.HomeAdapter
import com.talip.toodoo.ui.adapter.TodoAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(var tRepo: TodoRepository) : ViewModel() {
    var todoList = MutableLiveData<List<Todo>>()
    var todoDateList = MutableLiveData<List<String>>()


    init {

        getTodos()
        




    }


    fun getTodos() {

        CoroutineScope(Dispatchers.Main).launch {
            todoList.value = tRepo.getTodos()
            todoDateList.value = tRepo.getTodosDates()

        }


    }

    fun getTodosDates() {
        CoroutineScope(Dispatchers.Main).launch {

        }

    }

    fun save(todo_task: String, todo_date: String) {

        CoroutineScope(Dispatchers.Main).launch {
            tRepo.save(todo_task, todo_date)
        }

    }

    fun delete(todo_id : Int){
        CoroutineScope(Dispatchers.Main).launch {
            tRepo.delete(todo_id)
            getTodos()
        }
        
    }

    fun search(searchWord : String){
        CoroutineScope(Dispatchers.Main).launch {
           todoList.value = tRepo.search(searchWord)
           val list = ArrayList<String>()
            tRepo.search(searchWord).forEach {

                    list.add(it.todo_date)
                
            }
            todoDateList.value = list
        }

    }


}