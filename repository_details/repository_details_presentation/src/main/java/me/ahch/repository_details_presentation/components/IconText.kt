package me.ahch.repository_details_presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import me.ahch.core_ui.utils.DefaultStandardPadding

@Composable
fun IconText(icon: Int, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            modifier = Modifier
                .width(25.dp)
                .height(25.dp),
            imageVector = ImageVector.vectorResource(id = icon),
            contentDescription = text
        )
        Spacer(modifier = Modifier.width(DefaultStandardPadding))
        Text(text = text, style = MaterialTheme.typography.body1)
    }
}
