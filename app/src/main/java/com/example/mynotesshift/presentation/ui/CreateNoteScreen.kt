package com.example.mynotesshift.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mynotesshift.common.TextFields
import com.example.mynotesshift.presentation.viewmodels.NoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateNoteScreen(
    onClickBack: () -> Unit,
    onSaveClick: () -> Unit,
    viewModel: NoteViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(
                        onClick = onClickBack
                    ) {
                        Icon(
                            Icons.Default.ArrowBackIosNew,
                            contentDescription = "Back Main Screen"
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            viewModel.saveNote(
                                id = 0
                            )
                            onSaveClick()
                        }
                    ) {
                        Icon(
                            Icons.Default.Done,
                            contentDescription = "Save Note"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            TextFields(
                title = state.title,
                description = state.description,
                viewModel = viewModel
            )
        }
    }
}