package com.eddevios.collections.utils

import androidx.compose.runtime.*

@Composable
fun rememberDebouncedInput(
    input: String,
    delayMillis: Long = 300L
): String {
    var debouncedInput by remember { mutableStateOf(input) }
    LaunchedEffect(input) {
        kotlinx.coroutines.delay(delayMillis)
        debouncedInput = input
    }
    return debouncedInput
}
