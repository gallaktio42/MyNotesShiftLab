package com.example.mynotesshift.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.mynotesshift.R

@Composable
fun EmptyScreen(text: String, modifier: Modifier = Modifier) {
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.animate_thinking)
    )
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier.size(200.dp)
        )
        Spacer(Modifier.padding(vertical = 16.dp))
        Text(
            text = text,
            fontWeight = FontWeight.Light,
            fontSize = 24.sp,
            letterSpacing = 0.15.sp
        )
    }
}