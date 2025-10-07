package com.example.mynotesshift.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mynotesshift.data.local.entity.NotesEntity

@Dao
interface NotesDao {
    @Query("SELECT * FROM notes")
    suspend fun getAllNotes(): List<NotesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NotesEntity)

    @Query("SELECT * FROM notes WHERE id = :id")
    suspend fun getDetailOfNote(id: Int): NotesEntity

    @Query("DELETE FROM notes WHERE id = :id")
    suspend fun deleteNote(id: Int)
}