package me.ahch.repository_list_data.remote.dto

import retrofit2.Response

data class ResponseHolder(
    val data: Response<List<RepositoryDto>>,
    val userName: String
)