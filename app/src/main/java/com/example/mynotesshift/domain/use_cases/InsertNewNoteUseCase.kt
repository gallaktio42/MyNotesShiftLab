package com.example.mynotesshift.domain.use_cases

import com.example.mynotesshift.domain.data.NoteUIModel
import com.example.mynotesshift.domain.repository.NotesRepository
import javax.inject.Inject

class InsertNewNoteUseCase @Inject constructor(
    private val repository: NotesRepository
) {
    suspend operator fun invoke(note: NoteUIModel) {
        return repository.saveNote(note = note)
    }
}