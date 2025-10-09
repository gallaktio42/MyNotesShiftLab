package com.example.mynotesshift.presentation

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
