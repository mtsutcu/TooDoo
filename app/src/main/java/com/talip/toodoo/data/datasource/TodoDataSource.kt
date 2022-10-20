package com.talip.toodoo.data.datasource

import com.talip.toodoo.data.entity.Todo
import com.talip.toodoo.room.TodoDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TodoDataSource(var tDao : TodoDao) {

    suspend fun getTodos() : List<Todo> =
        withContext(Dispatchers.IO){
            tDao.getTodos()
        }

    suspend fun getTodosDates() : List<String> =
        withContext(Dispatchers.IO){
            tDao.getTodosDates()
        }

    suspend fun save(todo_task : String, todo_date : String) = withContext(Dispatchers.IO){
        val newTodo = Todo(0,todo_task,todo_date)

        tDao.save(newTodo)
    }

    suspend fun update(todo_id : Int, todo_task: String,todo_date: String){
        val updateTodo = Todo(todo_id,todo_task,todo_date)
        tDao.update(updateTodo)
    }

    suspend fun delete(todo_id :Int)  {
        val delTodo = Todo(todo_id,"","")

        tDao.delete(delTodo)
    }

    suspend fun search(searchWord : String) = withContext(Dispatchers.IO){
        tDao.search(searchWord)
    }
}