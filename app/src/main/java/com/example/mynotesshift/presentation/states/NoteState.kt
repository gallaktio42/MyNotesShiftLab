package com.example.mynotesshift.presentation.states

import com.example.mynotesshift.domain.data.NoteUIModel

data class NoteState(
    val data: NoteUIModel = NoteUIModel(
        id = 0,
        title = "",
        description = ""
    ),
    val title: String = "",
    val description: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)
