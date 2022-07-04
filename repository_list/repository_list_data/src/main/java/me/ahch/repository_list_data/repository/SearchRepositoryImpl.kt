package me.ahch.repository_list_data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import me.ahch.core.model.Repository
import me.ahch.core.utils.Resource
import me.ahch.repository_list_data.mapper.toRepository
import me.ahch.repository_list_data.remote.SearchApi
import me.ahch.repository_list_data.remote.dto.ResponseHolder
import me.ahch.repository_list_data.util.toLinedString
import me.ahch.repository_list_domain.repository.SearchRepository
import org.json.JSONObject


class SearchRepositoryImpl(
    val api: SearchApi
) : SearchRepository {

    override suspend fun searchRepository(
        userNames: List<String>,
        page: Int,
        perPage: Int
    ): Flow<Resource<List<Repository>>> {
        return flow {
            val repositoryList: MutableList<Repository> = mutableListOf()
            val errorList: MutableList<String> = mutableListOf()
            try {
                withContext(Dispatchers.IO) {
                    userNames.map { userName ->
                        async {
                            ResponseHolder(api.searchRepository(userName, page, perPage), userName)
                        }
                    }.forEach {
                        val responseHolder = it.await()
                        val response = responseHolder.data
                        if (response.isSuccessful) {
                            val repositoryDtoList = response.body()!!
                            repositoryList.addAll(repositoryDtoList.map {
                                it.toRepository()
                            })
                        } else {
                            val jObjError = JSONObject(response.errorBody()!!.string())
                            val errorMessage = jObjError.getString("message")
                            if (response.code() == 404) {
                                errorList.add("${responseHolder.userName}: $errorMessage")
                            } else {
                                errorList.add(errorMessage)
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.localizedMessage))
            }
            if (errorList.isNullOrEmpty().not()) {
                emit(Resource.Error(errorList.toLinedString()))
            }
            if (repositoryList.isNullOrEmpty().not()) {
                emit(Resource.Success(repositoryList))
            }

        }
    }
}

