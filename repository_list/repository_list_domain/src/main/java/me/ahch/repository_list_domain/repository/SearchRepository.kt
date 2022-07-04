package me.ahch.repository_list_domain.repository

import kotlinx.coroutines.flow.Flow
import me.ahch.core.model.Repository
import me.ahch.core.utils.Resource

interface SearchRepository {

    suspend fun searchRepository(userNames:List<String>, page:Int, perPage:Int) : Flow<Resource<List<Repository>>>
}