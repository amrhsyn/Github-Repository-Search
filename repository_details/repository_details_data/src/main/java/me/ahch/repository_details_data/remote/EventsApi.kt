package me.ahch.repository_details_data.remote

import me.ahch.repository_details_data.remote.dto.EventDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EventsApi {
    @GET("/repos/{userName}/{repository}/events")
    suspend fun getEvents(
        @Path("userName") userName: String,
        @Path("repository") repositoryName: String,
    ): Response<List<EventDto>>
}