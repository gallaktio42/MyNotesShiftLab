package com.example.mynotesshift.domain

import com.example.mynotesshift.data.local.entity.NotesEntity
import com.example.mynotesshift.presentation.NoteUIModel

fun NotesEntity.toUiModel(): NoteUIModel {
    return NoteUIModel(
        id = id,
        title = title,
        description = description
    )
}

fun NoteUIModel.toEntity(): NotesEntity {
    return NotesEntity(
        id = id,
        title = title,
        description = description
    )
}