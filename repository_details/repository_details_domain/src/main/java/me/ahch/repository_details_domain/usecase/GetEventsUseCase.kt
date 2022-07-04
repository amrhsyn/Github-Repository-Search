package me.ahch.repository_details_domain.usecase

import me.ahch.core.utils.Resource
import me.ahch.repository_details_domain.model.Event
import me.ahch.repository_details_domain.repository.EventsRepository
import javax.inject.Inject

class GetEventsUseCase @Inject constructor(
    private val repository: EventsRepository
) {
    suspend operator fun invoke(
        ownerName:String, repositoryName: String,
    ): Resource<List<Event>> = repository.getEvents(ownerName, repositoryName)
}
