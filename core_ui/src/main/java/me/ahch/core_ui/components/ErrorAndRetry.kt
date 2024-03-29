package me.ahch.core_ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import me.ahch.core_ui.R
import me.ahch.core_ui.utils.DefaultStandardPadding

@Composable
fun ErrorAndRetry(errorMessage: String, onRetryClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(text = errorMessage)
        Spacer(modifier = Modifier.width(DefaultStandardPadding))
        Text(
            text = stringResource(R.string.core_retry),
            style = TextStyle(color = MaterialTheme.colors.primary),
            modifier = Modifier.clickable {
                onRetryClick()
            })
    }
}