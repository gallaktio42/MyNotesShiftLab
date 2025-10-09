package com.example.mynotesshift.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynotesshift.domain.GetDetailNoteUseCase
import com.example.mynotesshift.domain.InsertNewNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val getDetailNoteUseCase: GetDetailNoteUseCase,
    private val insertNewNoteUseCase: InsertNewNoteUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(NoteState())
    val state = _state.asStateFlow()

    fun onChangeTitle(newTitle: String) {
        _state.update {
            it.copy(title = newTitle)
        }
    }

    fun onChangeDescription(newDescription: String) {
        _state.update {
            it.copy(description = newDescription)
        }
    }

    fun loadNote(id: Int) {
        _state.update { it.copy(isLoading = true, error = null) }
        viewModelScope.launch {
            try {
                val result = getDetailNoteUseCase(id = id)
                _state.update {
                    it.copy(
                        title = result.title,
                        description = result.description,
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _state.update { it.copy(error = e.message, isLoading = false) }
            }
        }
    }

    fun saveNote(id: Int) {

        val titleToSave =
            if (state.value.title.isBlank()) "Заголовок" else state.value.title

        val descriptionToSave =
            if (state.value.description.isBlank()) "Нет текста" else state.value.description

        viewModelScope.launch {
            try {
                if (id > 0) {
                    insertNewNoteUseCase(
                        note = NoteUIModel(
                            id = id,
                            title = titleToSave,
                            description = descriptionToSave,
                        )
                    )
                } else {
                    insertNewNoteUseCase(
                        note = NoteUIModel(
                            id = 0,
                            title = titleToSave,
                            description = descriptionToSave,
                        )
                    )
                }
            } catch (e: Exception) {
                _state.update { it.copy(error = "Failed to save note") }
            }
        }
    }
}