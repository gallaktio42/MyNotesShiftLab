package com.example.mynotesshift.domain

import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor(
    private val repository: NotesRepository
) {
    suspend operator fun invoke(id: Int) {
        repository.deleteNote(id = id)
    }
}