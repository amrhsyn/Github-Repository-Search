package me.ahch.core_ui.paging

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import me.ahch.core_ui.R

/**
 * A lazy list to show items gradually. It informs when reaches the end of the list to
 * to get new items
 *
 * @param state is to understand the state of the list. It can be either [PagingStateWrapper.Loading],
 * [PagingStateWrapper.Failure], [PagingStateWrapper.Finished], and [[PagingStateWrapper.None]
 * @param items to show in the list
 * @param pageSize to understand when get new page
 * @param showScrollUpButton when is true by scrolling list a button appears that by hitting it the list
 * scrolls up to the first item
 * @param loadingIndicator shows a loading indicator at the end of the list (after last item) when the [state] is equal to [PagingStateWrapper.Loading]
 * @param finishIndicator shows an indicator at the end of the list (after last item) when the [state] is equal to [PagingStateWrapper.Finished]
 * @param onGetNextPage will be called when it is necessary to inform to the next page
 * @param headerContent is header section of the list
 * @param emptyContent is content of page if list is empty
 * @param itemContent is item of the list
 */
@Composable
fun <T> Paging(
    state: PagingStateWrapper,
    items: List<T>,
    pageSize: Int = 20,
    showScrollUpButton: Boolean = true,
    lazyListState: LazyListState = rememberLazyListState(),
    loadingIndicator: (@Composable () -> Unit)? = null,
    finishIndicator: (@Composable () -> Unit)? = null,
    onGetNextPage: (pageNumber: Int) -> Unit,
    headerContent: (@Composable () -> Unit)? = null,
    emptyContent: (@Composable () -> Unit)? = null,
    errorAndRetry: (@Composable (String,Int) -> Unit),
    itemContent: @Composable (index: Int, item: T) -> Unit
) {

    val coroutineScope = rememberCoroutineScope()
    var isReadyToGetNextPage by remember { mutableStateOf(true) }
    val destiny = LocalDensity.current
    val height = LocalConfiguration.getHeight()

    Box(modifier = Modifier.fillMaxWidth()) {

        if (items.isNotEmpty()) {
            LazyColumn(
                state = lazyListState,
                modifier = Modifier
                    .fillMaxSize()
                    .testTag("Paging")
            ) {
                //section to show header on top of the list
                item {
                    if (headerContent != null) {
                        headerContent()
                    }
                }

                itemsIndexed(items) { index, item ->
                    itemContent(index, item)

                    if (state is PagingStateWrapper.None && (index == items.lastIndex - 5) && isReadyToGetNextPage) {
                        isReadyToGetNextPage = false
                        onGetNextPage.invoke(items.size / pageSize + 1)
                    }
                }

                if (state is PagingStateWrapper.Loading && loadingIndicator != null) {
                    item { loadingIndicator() }
                    return@LazyColumn
                }
                if (state is PagingStateWrapper.Failure) {
                    item { errorAndRetry(state.message,items.size / pageSize) }
                    return@LazyColumn
                }

                if (state is PagingStateWrapper.Finished && finishIndicator != null) {
                    item { finishIndicator() }
                }
            }
        } else {
            if (state is PagingStateWrapper.Finished && emptyContent != null) {
                emptyContent()
            }
        }


        // Scroll Up Button
        if (showScrollUpButton) {
            Box(Modifier.align(Alignment.BottomEnd)) {
                AnimatedVisibility(
                    visible = lazyListState.firstVisibleItemIndex > 3,
                    enter = slideInVertically {
                        with(destiny) { height + 40.dp.roundToPx() }
                    } + fadeIn(),
                    exit = slideOutVertically {
                        with(destiny) { height + 40.dp.roundToPx() }
                    } + fadeOut()
                ) {
                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                            .size(35.dp)
                            .shadow(2.dp, CircleShape)
                            .background(MaterialTheme.colors.surface, CircleShape)
                            .align(Alignment.BottomEnd)
                            .clickable {
                                coroutineScope.launch { lazyListState.scrollToItem(0) }
                            }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_arrow_up),
                            contentDescription = null,
                            Modifier.align(Alignment.Center),
                            colorFilter = ColorFilter.tint(MaterialTheme.colors.onBackground)
                        )
                    }
                }
            }
        }

    }

    if (state is PagingStateWrapper.None) {
        isReadyToGetNextPage = true
        if (state.refresh) {
            LaunchedEffect(state.refresh) {
                lazyListState.scrollToItem(0)
                state.refresh = false
            }
        }
    }

}