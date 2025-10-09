package com.example.mynotesshift.domain.use_cases

import com.example.mynotesshift.domain.data.NoteUIModel
import com.example.mynotesshift.domain.repository.NotesRepository
import javax.inject.Inject

class GetNotesUseCase @Inject constructor(
    private val repository: NotesRepository
) {
    suspend operator fun invoke(): List<NoteUIModel> {
        return repository.getNotes()
    }
}