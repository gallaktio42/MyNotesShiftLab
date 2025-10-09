package com.example.mynotesshift.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    id: Int,
    onClickBack: () -> Unit,
    onSaveClick: () -> Unit,
    viewModel: NoteViewModel = hiltViewModel(),
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.loadNote(id = id)
    }

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
                                id = id
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
            OutlinedTextField(
                value = state.title,
                onValueChange = viewModel::onChangeTitle,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Start),
                textStyle = TextStyle(
                    fontWeight = FontWeight.W500,
                    fontSize = 24.sp,
                    letterSpacing = 0.15.sp
                ),
                placeholder = {
                    Text(
                        text = "Заголовок",
                        fontWeight = FontWeight.W500,
                        fontSize = 24.sp,
                        letterSpacing = 0.15.sp,
                        color = MaterialTheme.colorScheme.onSecondaryContainer.copy(0.6f)
                    )
                },
                supportingText = {
                    Text(
                        text = "${state.description.length} символов",
                        fontWeight = FontWeight.W400,
                        fontSize = 14.sp,
                        lineHeight = 20.sp,
                        color = MaterialTheme.colorScheme.secondary.copy(0.5f)
                    )
                },
                maxLines = 1,
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent,
                    errorBorderColor = Color.Transparent,
                )
            )
            Spacer(Modifier.padding(vertical = 2.dp))
            OutlinedTextField(
                value = state.description,
                onValueChange = viewModel::onChangeDescription,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                textStyle = TextStyle(
                    fontWeight = FontWeight.W400,
                    fontSize = 14.sp,
                    letterSpacing = 1.sp,
                    lineHeight = 20.sp,
                ),
                placeholder = {
                    Text(
                        text = "Начните ввод",
                        fontWeight = FontWeight.W400,
                        fontSize = 14.sp,
                        letterSpacing = 1.sp,
                        lineHeight = 20.sp,
                        color = MaterialTheme.colorScheme.onSecondaryContainer.copy(0.6f)
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent,
                    errorBorderColor = Color.Transparent,
                )
            )
        }
    }
}