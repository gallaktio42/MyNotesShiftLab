package com.example.mynotesshift.domain.data_mappers

import com.example.mynotesshift.data.local.entity.NotesEntity
import com.example.mynotesshift.domain.data.NoteUIModel

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