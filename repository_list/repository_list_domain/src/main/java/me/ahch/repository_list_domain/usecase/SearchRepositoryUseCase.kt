package me.ahch.repository_list_domain.usecase

import kotlinx.coroutines.flow.Flow
import me.ahch.core.model.Repository
import me.ahch.core.utils.Resource
import me.ahch.repository_list_domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryUseCase @Inject constructor(
    private val repository: SearchRepository
) {
    suspend operator fun invoke(
        userNames: List<String>,
        page: Int,
        perPage: Int
    ): Flow<Resource<List<Repository>>> = repository.searchRepository(userNames, page, perPage)
}
