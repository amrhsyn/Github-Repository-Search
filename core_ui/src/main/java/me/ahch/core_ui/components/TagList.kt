package me.ahch.core_ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import me.ahch.core_ui.utils.ChipsElevation
import me.ahch.core_ui.utils.ChipsRoundedCorner
import me.ahch.core_ui.utils.DefaultSmallPadding
import me.ahch.core_ui.utils.DefaultStandardPadding


@Composable
fun TagList(tagList: List<String>) {
    LazyRow {
        items(items = tagList) { tag ->
            Surface(
                modifier = Modifier.padding(
                    end = DefaultSmallPadding
                ),
                elevation = ChipsElevation,
                shape = RoundedCornerShape(ChipsRoundedCorner),
            ) {
                Text(
                    tag,
                    style = MaterialTheme.typography.caption,
                    color = Color.Black,
                    modifier = Modifier.padding(DefaultStandardPadding)
                )
            }
        }
    }
}