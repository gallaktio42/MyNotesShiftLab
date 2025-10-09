package com.example.mynotesshift.domain.repository

import com.example.mynotesshift.domain.data.NoteUIModel

interface NotesRepository {
    suspend fun getNotes(): List<NoteUIModel>
    suspend fun saveNote(note: NoteUIModel)
    suspend fun getDetail(id: Int): NoteUIModel
    suspend fun deleteNote(id: Int)
}