package me.ahch.repository_details_presentation

import me.ahch.repository_details_domain.model.Event

data class DetailsState(
    val eventsList: List<Event> = listOf(),
    val errorMessage: String = "",
)
