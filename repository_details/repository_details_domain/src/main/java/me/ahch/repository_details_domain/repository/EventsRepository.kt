package me.ahch.repository_details_domain.repository

import me.ahch.core.utils.Resource
import me.ahch.repository_details_domain.model.Event

interface EventsRepository {

    suspend fun getEvents(ownerName:String, repositoryName: String) : Resource<List<Event>>
}