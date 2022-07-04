package me.ahch.repository_details_data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.ahch.core.utils.Resource
import me.ahch.repository_details_data.mapper.toEvent
import me.ahch.repository_details_data.remote.EventsApi
import me.ahch.repository_details_domain.model.Event
import me.ahch.repository_details_domain.repository.EventsRepository


class EventRepositoryImpl(
    val api: EventsApi
) : EventsRepository {

    override suspend fun getEvents(
        ownerName: String,
        repositoryName: String
    ): Resource<List<Event>> = withContext(Dispatchers.IO) {
        try {
            val result = api.getEvents(ownerName, repositoryName)
            return@withContext if (result.isSuccessful) {
                Resource.Success(result.body()!!.map { it.toEvent() })
            } else {
                Resource.Error(result.errorBody().toString())
            }

        } catch (e: Exception) {
            return@withContext Resource.Error(e.localizedMessage.toString())
        }
    }
}

