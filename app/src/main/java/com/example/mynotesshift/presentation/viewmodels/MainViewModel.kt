package com.example.mynotesshift.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynotesshift.domain.data.NoteUIModel
import com.example.mynotesshift.domain.use_cases.DeleteNoteUseCase
import com.example.mynotesshift.domain.use_cases.GetNotesUseCase
import com.example.mynotesshift.presentation.states.MainState
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

    private val _filteredNotes = MutableStateFlow<List<NoteUIModel>>(emptyList())
    val filteredNotes = _filteredNotes.asStateFlow()

    fun onChangeSearch(newSearch: String) {
        val filtered = filterNotes(_notes.value, newSearch)
        _state.value = _state.value.copy(
            search = newSearch,
        )
        _filteredNotes.value = filtered
    }

    fun loadNotes() {
        _state.update { it.copy(isLoading = true, error = null) }
        viewModelScope.launch {
            try {
                val result = getNotesUseCase().asReversed()
                _notes.value = result
                _state.update { it.copy(isLoading = false) }
                _filteredNotes.value = filterNotes(result, _state.value.search)

            } catch (e: Exception) {
                _state.update { it.copy(error = e.message, isLoading = false) }
            }
        }
    }

    fun deleteNote(id: Int) {
        viewModelScope.launch {
            try {
                deleteNoteUseCase(id)
                _filteredNotes.value = _filteredNotes.value.filter { it.id != id }
                _notes.value = _notes.value.filter { it.id != id }
            } catch (e: Exception) {
                _state.update { it.copy(error = "Failed to delete note") }
            }
        }
    }

    private fun filterNotes(notes: List<NoteUIModel>, query: String): List<NoteUIModel> {
        return if (query.isBlank()) {
            notes
        } else {
            notes.filter { note ->
                note.title.contains(
                    query,
                    ignoreCase = true
                ) || note.description.contains(
                    query,
                    ignoreCase = true
                )
            }
        }
    }
}