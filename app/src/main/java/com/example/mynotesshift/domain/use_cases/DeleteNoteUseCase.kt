package com.example.mynotesshift.domain.use_cases

import com.example.mynotesshift.domain.repository.NotesRepository
import javax.inject.Inject

class DeleteNoteUseCase @Inject constructor(
    private val repository: NotesRepository
) {
    suspend operator fun invoke(id: Int) {
        repository.deleteNote(id = id)
    }
}