package com.example.mynotesshift.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mynotesshift.R

@Composable
fun ErrorScreen(
    errorText: String,
    onRetry: () -> Unit
) {
    Column(
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = errorText,
            color = MaterialTheme.colorScheme.error,
            fontWeight = FontWeight.W500,
            fontSize = 20.sp,
            letterSpacing = 0.15.sp
        )
        Spacer(Modifier.padding(vertical = 16.dp))
        Button(
            onClick = onRetry
        ) {
            Text(
                text = stringResource(R.string.error_button)
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    ErrorScreen("error", onRetry = {})
}