package me.ahch.repository_details_presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import me.ahch.core.model.Repository
import me.ahch.core_ui.utils.DefaultLargePadding
import me.ahch.core_ui.utils.DefaultStandardPadding
import me.ahch.repository_details_domain.model.Event

@Composable
fun EventItem(event: Event) {

    Card(
        modifier = Modifier
            .padding(DefaultStandardPadding)
            .fillMaxWidth(),
        shape = RoundedCornerShape(DefaultStandardPadding),
        backgroundColor = MaterialTheme.colors.surface,
    ) {
        Column(
            modifier = Modifier
                .padding(DefaultLargePadding),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center,
        ) {
            Spacer(modifier = Modifier.height(DefaultStandardPadding))
            Text(
                text = event.type,
                color = Color.DarkGray,
                style = MaterialTheme.typography.h6
            )
            Spacer(modifier = Modifier.height(DefaultStandardPadding))
            Text(
                text = event.actorDisplayLogin,
                color = Color.DarkGray,
                style = MaterialTheme.typography.subtitle1
            )
            Spacer(modifier = Modifier.height(DefaultStandardPadding))
            Text(
                text = event.actorUrl,
                color = Color.DarkGray,
                style = MaterialTheme.typography.subtitle1
            )
            Spacer(modifier = Modifier.height(DefaultStandardPadding))

        }
    }
}