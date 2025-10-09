package com.example.mynotesshift.domain

import com.example.mynotesshift.presentation.NoteUIModel

interface NotesRepository {
    suspend fun getNotes(): List<NoteUIModel>
    suspend fun saveNote(note: NoteUIModel)
    suspend fun getDetail(id: Int): NoteUIModel
    suspend fun deleteNote(id: Int)
}