package com.example.mynotesshift.data.repository

import com.example.mynotesshift.data.local.dao.NotesDao
import com.example.mynotesshift.domain.NotesRepository
import com.example.mynotesshift.domain.toEntity
import com.example.mynotesshift.domain.toUiModel
import com.example.mynotesshift.presentation.NoteUIModel
import javax.inject.Inject

class NotesRepositoryImpl @Inject constructor(
    private val dao: NotesDao
) : NotesRepository {

    override suspend fun getNotes(): List<NoteUIModel> {
        return dao.getAllNotes().map { it.toUiModel() }
    }

    override suspend fun saveNote(note: NoteUIModel) {
        val entity = note.toEntity()
        dao.insertNote(note = entity)
    }

    override suspend fun getDetail(id: Int): NoteUIModel {
        return dao.getDetailOfNote(id = id).toUiModel()
    }

    override suspend fun deleteNote(id: Int) {
        dao.deleteNote(id = id)
    }

}