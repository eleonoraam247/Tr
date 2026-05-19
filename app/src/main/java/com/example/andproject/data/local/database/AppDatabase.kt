package com.example.andproject.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.andproject.data.local.dao.TaskDao
import com.example.andproject.data.local.entity.TaskEntity

@Database(entities = [TaskEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val taskDao: TaskDao

    companion object {
        const val DATABASE_NAME = "levelup_db"
    }
}
