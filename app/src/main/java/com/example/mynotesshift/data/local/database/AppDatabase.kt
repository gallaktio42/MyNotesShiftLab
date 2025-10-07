package com.example.mynotesshift.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mynotesshift.data.local.dao.NotesDao
import com.example.mynotesshift.data.local.entity.NotesEntity

@Database(
    entities = [NotesEntity::class],
    exportSchema = false,
    version = 1
)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun notesDao(): NotesDao
}