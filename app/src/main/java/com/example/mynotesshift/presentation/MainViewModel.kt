package com.example.mynotesshift.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynotesshift.domain.DeleteNoteUseCase
import com.example.mynotesshift.domain.GetNotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getNotesUseCase: GetNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()

    private val _notes = MutableStateFlow<List<NoteUIModel>>(emptyList())
    val notes = _notes.asStateFlow()

    fun loadNotes() {
        _state.update { it.copy(isLoading = true, error = null) }
        viewModelScope.launch {
            try {
                _notes.value = getNotesUseCase()
                _state.update { it.copy(isLoading = false) }
            } catch (e: Exception) {
                _state.update { it.copy(error = e.message, isLoading = false) }
            }
        }
    }

    fun deleteNote(id: Int) {
        viewModelScope.launch {
            try {
                deleteNoteUseCase(id)
                _notes.value = _notes.value.filter { it.id != id }
            } catch (e: Exception) {
                _state.update { it.copy(error = "Failed to delete note") }
            }
        }
    }
}