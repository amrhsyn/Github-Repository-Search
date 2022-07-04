package me.ahch.repository_list_presentation.components

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
import androidx.compose.ui.unit.dp
import me.ahch.core.model.Repository
import me.ahch.core_ui.utils.*

@Composable
fun SearchItem(repository: Repository, onItemClick: () -> Unit) {

    Card(
        modifier = Modifier
            .clickable { onItemClick() }
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
                text = repository.name,
                color = Color.DarkGray,
                style = MaterialTheme.typography.h6
            )
            Spacer(modifier = Modifier.height(DefaultStandardPadding))
            Text(
                text = repository.owner,
                color = Color.DarkGray,
                style = MaterialTheme.typography.subtitle1
            )
            Spacer(modifier = Modifier.height(DefaultStandardPadding))

        }
    }
}