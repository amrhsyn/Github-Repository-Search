package me.ahch.repository_list_presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import me.ahch.core.model.Repository
import me.ahch.core_ui.paging.Paging
import me.ahch.core_ui.utils.CircularProgressIndicatorSize
import me.ahch.core_ui.utils.UiEvent
import me.ahch.core_ui.components.ErrorAndRetry
import me.ahch.repository_list_presentation.components.SearchItem
import me.ahch.repository_list_presentation.components.SearchView

@Composable
fun SearchScreen(
    scaffoldState: ScaffoldState,
    viewModel: SearchViewModel,
    navigateToDetailsScreen: (Repository) -> Unit
) {


    val state by viewModel.state.collectAsState()
    val context = LocalContext.current
    val lazyListState = rememberLazyListState()

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message.asString(context = context)
                    )
                }
                else -> Unit
            }
        }
    }

    if (state.showLoadingIndicator && state.repositoryList.isEmpty()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .width(CircularProgressIndicatorSize)
                    .height(CircularProgressIndicatorSize)
            )

        }
    }


    Scaffold(
        scaffoldState = scaffoldState,
        backgroundColor = Color(0xFFDFDFDF),
        topBar = {
            SearchView(
                modifier = Modifier,
                text = state.searchedValue,
                onTextChange = {
                    viewModel.onEvent(SearchEvent.OnTextChange(it))
                }, onSearchClick = {
                    viewModel.onEvent(SearchEvent.OnSearchClick(it))
                }, onCloseClick = {
                    viewModel.onEvent(SearchEvent.OnCloseClick)
                })
        }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            Paging(
                state = state.repositoryListState,
                items = state.repositoryList,
                lazyListState = lazyListState,
                onGetNextPage = {
                    viewModel.searchRepository(page = it)
                },
                loadingIndicator = {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .width(CircularProgressIndicatorSize)
                                .height(CircularProgressIndicatorSize)
                                .align(Alignment.Center)
                        )
                    }
                },
                errorAndRetry = { errorMessage, page ->
                    ErrorAndRetry(errorMessage = errorMessage) {
                        viewModel.searchRepository(page = page)
                    }
                },
                emptyContent = {}
            ) { _, item ->
                SearchItem(repository = item) {
                    navigateToDetailsScreen(item)
                }
            }
        }

    }
}