package com.example.mynotesshift.domain

import com.example.mynotesshift.presentation.NoteUIModel
import javax.inject.Inject

class InsertNewNoteUseCase @Inject constructor(
    private val repository: NotesRepository
) {
    suspend operator fun invoke(note: NoteUIModel) {
        return repository.saveNote(note = note)
    }
}