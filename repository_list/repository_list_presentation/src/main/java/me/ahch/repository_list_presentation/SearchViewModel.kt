package me.ahch.repository_list_presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import me.ahch.core.utils.Resource
import me.ahch.core_ui.paging.PagingStateWrapper
import me.ahch.core_ui.utils.UiEvent
import me.ahch.core_ui.utils.UiText
import me.ahch.repository_list_domain.usecase.SearchRepositoryUseCase
import me.ahch.repository_list_presentation.utils.separateBySpace
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchRepositoryUseCase: SearchRepositoryUseCase) :
    ViewModel() {

    private val perPage = 20

    private var _state = MutableStateFlow(SearchState())
    val state = _state

    private var job: Job? = null

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        searchRepository("infinum JakeWharton")
        _state.value = state.value.copy(searchedValue = "infinum JakeWharton")
    }

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.OnTextChange -> {
                _state.value = state.value.copy(searchedValue = event.text)
                // searchRepository()
            }
            is SearchEvent.OnSearchClick -> {
                _state.value = state.value.copy(searchedValue = event.text, repositoryList = listOf())
                searchRepository()
            }
            is SearchEvent.OnCloseClick -> {
                _state.value = state.value.copy(searchedValue = "")
            }
        }
    }


    fun searchRepository(userNames: String = state.value.searchedValue, page: Int = 1) {
        if (userNames.length >= 3) {
            job?.cancel()
            _state.value = state.value.copy(
                repositoryListState = PagingStateWrapper.Loading,
                showLoadingIndicator = page == 1
            )
            job = viewModelScope.launch {
               searchRepositoryUseCase.invoke(
                    userNames = userNames.separateBySpace(),
                    page,
                    perPage
                ).collect{ result ->
                        when (result) {
                            is Resource.Success -> {
                                _state.value =
                                    state.value.copy(
                                        repositoryList = state.value.repositoryList + result.data,
                                        showLoadingIndicator = false,
                                        repositoryListState = if (result.data.size < perPage - 1) PagingStateWrapper.Finished else PagingStateWrapper.None(
                                            page == 0
                                        )
                                    )
                            }
                            is Resource.Error -> {
                                _state.value =
                                    state.value.copy(
                                        showLoadingIndicator = false,
                                        repositoryListState = PagingStateWrapper.Failure(result.message)
                                    )
                                _uiEvent.send(
                                    UiEvent.ShowSnackbar(
                                        if (result.message.isNullOrEmpty()) {
                                            UiText.StringResource(R.string.search_screen_error_something_went_wrong)
                                        } else {
                                            UiText.DynamicString(result.message)
                                        }
                                    )
                                )
                            }
                        }
                    }

            }
        }
    }

}