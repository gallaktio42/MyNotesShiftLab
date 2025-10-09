package com.example.mynotesshift.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mynotesshift.R
import com.example.mynotesshift.common.EmptyScreen
import com.example.mynotesshift.common.ErrorScreen
import com.example.mynotesshift.common.LoadingScreen
import com.example.mynotesshift.common.SearchBar
import com.example.mynotesshift.presentation.viewmodels.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onClick: () -> Unit,
    onClickCard: (Int) -> Unit,
    viewModel: MainViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val filteredNotes by viewModel.filteredNotes.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.loadNotes()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_name_main),
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
        if (state.isLoading) {
            LoadingScreen()
        } else if (state.error?.isNotEmpty() == true) {
            ErrorScreen(state.error ?: "") {
                viewModel.loadNotes()
            }
        } else {
            if (filteredNotes.isEmpty() && state.search.isBlank()) {
                EmptyScreen(
                    text = stringResource(R.string.empty_list),
                    modifier = Modifier.fillMaxSize()
                )
            } else if (state.search.isNotBlank() && filteredNotes.isEmpty()) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    SearchBar(
                        search = state.search,
                        onValueChange = {
                            viewModel.onChangeSearch(it)
                        },
                    )
                    Spacer(Modifier.padding(vertical = 5.dp))
                    EmptyScreen(
                        text = stringResource(R.string.empty_search),
                        modifier = Modifier.weight(1f)
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .padding(paddingValues)
                        .padding(horizontal = 16.dp)
                ) {
                    item {
                        SearchBar(
                            search = state.search,
                            onValueChange = {
                                viewModel.onChangeSearch(it)
                            },
                        )
                        Spacer(Modifier.padding(vertical = 5.dp))
                    }
                    items(
                        filteredNotes,
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
    }
}