package me.ahch.repository_details_data.mapper

import me.ahch.repository_details_data.remote.dto.EventDto
import me.ahch.repository_details_domain.model.Event

fun EventDto.toEvent(): Event = Event(
    type = type, actorDisplayLogin = actor.displayLogin, actorUrl = actor.url
)
