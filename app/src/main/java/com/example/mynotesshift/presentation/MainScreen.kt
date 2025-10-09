package com.example.mynotesshift.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onClick: () -> Unit,
    onClickCard: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val notes by viewModel.notes.collectAsStateWithLifecycle()
    var notesList = remember {  mutableStateListOf<List<NoteUIModel>>() }

    LaunchedEffect(Unit) {
        viewModel.loadNotes()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Мои заметки",
                        fontWeight = FontWeight.W500,
                        fontSize = 20.sp,
                        letterSpacing = 0.15.sp
                    )
                },
                actions = {
                    IconButton(
                        onClick = onClick
                    ) {
                        Icon(
                            Icons.Default.Add,
                            contentDescription = "New Note"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            items(
                notes,
                key = { it.id }
            ) { item ->
                NoteCardItem(
                    item = item,
                    onNavigate = {
                        onClickCard(item.id)
                    },
                    onRemove = {
                        viewModel.deleteNote(id = item.id)
                    }
                )
                Spacer(Modifier.padding(vertical = 5.dp))
            }
        }
    }
}