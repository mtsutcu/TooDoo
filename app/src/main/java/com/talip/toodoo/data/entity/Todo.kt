package com.talip.toodoo.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.io.Serializable


@Entity(tableName = "Todo")
data class Todo(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("todo_id") @NotNull var todo_id : Int ,
    @ColumnInfo("todo_task") @NotNull var todo_task : String,
    @ColumnInfo("todo_date") @NotNull var todo_date : String) : Serializable {}
