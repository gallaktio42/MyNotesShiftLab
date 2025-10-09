package com.example.mynotesshift.domain

import com.example.mynotesshift.presentation.NoteUIModel
import javax.inject.Inject

class GetNotesUseCase @Inject constructor(
    private val repository: NotesRepository
) {
    suspend operator fun invoke(): List<NoteUIModel> {
        return repository.getNotes()
    }
}