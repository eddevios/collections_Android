package com.eddevios.collections.utils

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle

@Composable
fun HighlightedText(text: String, query: String) {
    val startIndex = text.indexOf(query, ignoreCase = true)
    if (startIndex != -1) {
        val endIndex = startIndex + query.length
        Text(buildAnnotatedString {
            append(text.substring(0, startIndex))
            withStyle(style = SpanStyle(color = Color.Red)) {
                append(text.substring(startIndex, endIndex))
            }
            append(text.substring(endIndex))
        })
    } else {
        Text(text)
    }
}
