package com.talip.toodoo.room

import androidx.room.*
import com.talip.toodoo.data.entity.Todo

@Dao
interface TodoDao {
    @Query("SELECT * FROM Todo")
    suspend fun getTodos(): List<Todo>

    @Query("SELECT todo_date FROM Todo")
    suspend fun getTodosDates(): List<String>

    @Insert
    suspend fun save(todo: Todo)

    @Update
    suspend fun update(todo: Todo)

    @Delete
    suspend fun delete(todo : Todo)

    @Query("SELECT * FROM Todo WHERE todo_task like '%' || :searchWord || '%'")
    suspend fun search(searchWord : String) : List<Todo>
  
}