package com.example.mynotesshift.domain

import com.example.mynotesshift.presentation.NoteUIModel
import javax.inject.Inject

class GetDetailNoteUseCase @Inject constructor(
    private val repository: NotesRepository
) {
    suspend operator fun invoke(id: Int): NoteUIModel {
        return repository.getDetail(id = id)
    }
}