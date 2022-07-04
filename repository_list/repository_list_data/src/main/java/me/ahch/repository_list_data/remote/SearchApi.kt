package me.ahch.repository_list_data.remote

import me.ahch.repository_list_data.remote.dto.RepositoryDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchApi {
    @GET("/users/{userName}/repos")
    suspend fun searchRepository(
        @Path("userName") userName: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<List<RepositoryDto>>
}