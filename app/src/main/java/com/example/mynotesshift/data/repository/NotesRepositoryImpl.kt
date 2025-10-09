package com.example.mynotesshift.data.repository

import com.example.mynotesshift.data.local.dao.NotesDao
import com.example.mynotesshift.domain.data.NoteUIModel
import com.example.mynotesshift.domain.data_mappers.toEntity
import com.example.mynotesshift.domain.data_mappers.toUiModel
import com.example.mynotesshift.domain.repository.NotesRepository
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