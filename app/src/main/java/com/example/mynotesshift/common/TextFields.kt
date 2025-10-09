package com.example.mynotesshift.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mynotesshift.R
import com.example.mynotesshift.presentation.viewmodels.NoteViewModel

@Composable
fun TextFields(
    title: String,
    description: String,
    viewModel: NoteViewModel
) {
    Column {
        OutlinedTextField(
            value = title,
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
                    text = stringResource(R.string.title),
                    fontWeight = FontWeight.W500,
                    fontSize = 24.sp,
                    letterSpacing = 0.15.sp,
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(0.6f)
                )
            },
            supportingText = {
                Text(
                    text = stringResource(R.string.chars, description.length),
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
            value = description,
            onValueChange = viewModel::onChangeDescription,
            modifier = Modifier
                .weight(1f),
            textStyle = TextStyle(
                fontWeight = FontWeight.W400,
                fontSize = 14.sp,
                letterSpacing = 1.sp,
                lineHeight = 20.sp,
            ),
            placeholder = {
                Text(
                    text = stringResource(R.string.enter_text),
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