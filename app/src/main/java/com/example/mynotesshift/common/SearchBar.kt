package com.example.mynotesshift.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.mynotesshift.R

@Composable
fun SearchBar(
    search: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = search,
        onValueChange = {
            onValueChange(it)
        },
        Modifier.fillMaxWidth(),
        leadingIcon = {
            Icon(
                Icons.Rounded.Search,
                contentDescription = "Search Notes"
            )
        },
        placeholder = {
            Text(
                text = stringResource(R.string.search_notes)
            )
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        maxLines = 1,
        shape = RoundedCornerShape(33.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainerHigh
        )
    )
}