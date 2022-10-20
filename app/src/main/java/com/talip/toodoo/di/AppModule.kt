package com.talip.toodoo.di

import android.content.Context
import androidx.room.Dao
import androidx.room.Room
import com.talip.toodoo.data.datasource.TodoDataSource
import com.talip.toodoo.data.repo.TodoRepository
import com.talip.toodoo.room.MyDatabase
import com.talip.toodoo.room.TodoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideTodoRepository(tds : TodoDataSource) : TodoRepository{
        return TodoRepository(tds)
    }

    @Provides
    @Singleton
    fun provideTodoDataSource(tDao: TodoDao ) : TodoDataSource{
        return TodoDataSource(tDao)
    }

    @Provides
    @Singleton
    fun provideTodoDao(@ApplicationContext context: Context):TodoDao{
        val db = Room.databaseBuilder(context,MyDatabase::class.java,"todo.sqlite")
            .createFromAsset("todo.sqlite").build()
        return db.getTodoDao()
    }
}