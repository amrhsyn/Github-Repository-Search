package me.ahch.repository_list_presentation

import me.ahch.core.model.Repository
import me.ahch.core_ui.paging.PagingStateWrapper


data class SearchState(
    val repositoryListState:PagingStateWrapper = PagingStateWrapper.None(true),
    val repositoryList: List<Repository> = listOf(),
    val searchedValue: String = "",
    val showLoadingIndicator: Boolean = false
)
