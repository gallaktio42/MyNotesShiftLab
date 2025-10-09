package com.example.mynotesshift.presentation.states

data class MainState(
    val search: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)
