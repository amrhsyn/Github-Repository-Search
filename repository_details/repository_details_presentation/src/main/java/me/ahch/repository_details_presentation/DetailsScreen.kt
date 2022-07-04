package me.ahch.repository_details_presentation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import me.ahch.core.model.Repository

@Composable
fun DetailsScreen(repository: Repository){
    Text(repository.url)
}