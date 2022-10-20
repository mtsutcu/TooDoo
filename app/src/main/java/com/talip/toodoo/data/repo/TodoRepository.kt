package com.talip.toodoo.data.repo

import com.talip.toodoo.data.datasource.TodoDataSource
import com.talip.toodoo.data.entity.Todo

class TodoRepository(var tds: TodoDataSource) {

    suspend fun getTodos(): List<Todo> = tds.getTodos()

    suspend fun getTodosDates(): List<String> = tds.getTodosDates()
    suspend fun save(todo_task: String, todo_date: String) = tds.save(todo_task, todo_date)
    suspend fun update(todo_id : Int,todo_task: String,todo_date: String) = tds.update(todo_id ,todo_task,todo_date)
    suspend fun delete(todo_id : Int)= tds.delete(todo_id)
    suspend fun search(searchWord : String) = tds.search(searchWord)

}