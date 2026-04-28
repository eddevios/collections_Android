package com.eddevios.collections.utils

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.res.stringResource
import com.eddevios.collections.R
import androidx.compose.foundation.clickable
import androidx.compose.ui.res.painterResource

@Composable
fun EmptyStateView(
    modifier: Modifier = Modifier,
    iconResId: Int = R.drawable.square_stack_3d_up_slash,
    primaryText: String,
    secondaryText: String,
    buttonText: String,
    onButtonClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = iconResId),

            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier
                .size(64.dp)
                .clickable(onClick = onButtonClick)
        )
        Spacer(Modifier.height(16.dp))
        Text(
            primaryText,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(Modifier.height(8.dp))
        Text(
            secondaryText,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(Modifier.height(24.dp))
        Button(onClick = onButtonClick) {
            Text(buttonText)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyCollectionsViewPreview() {
    EmptyStateView(
        iconResId = R.drawable.square_stack_3d_up_slash,
        primaryText   = stringResource(R.string.no_collections_title),
        secondaryText = stringResource(R.string.no_collections_title),
        buttonText    = stringResource(R.string.create_collection),
        onButtonClick = { /* Acción de ejemplo */ }
    )
}
