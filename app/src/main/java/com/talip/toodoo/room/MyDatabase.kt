package com.talip.toodoo.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.talip.toodoo.data.entity.Todo


@Database(entities = [Todo::class], version = 1, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {
    abstract fun getTodoDao() : TodoDao
}