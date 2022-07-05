package me.ahch.repository_details_presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import me.ahch.core.model.Repository
import me.ahch.core_ui.utils.DefaultLargePadding
import me.ahch.core_ui.utils.DefaultStandardPadding
import me.ahch.core_ui.utils.UiEvent
import me.ahch.repository_details_presentation.components.EventItem
import me.ahch.repository_details_presentation.components.IconText

@Composable
fun DetailsScreen(
    scaffoldState: ScaffoldState,
    viewModel: DetailsViewModel,
    repository: Repository,
    onBackPress: () -> Unit
) {

    val context = LocalContext.current

    val state = viewModel.state.collectAsState()
    LaunchedEffect(key1 = true) {
        viewModel.getEvents(repository.owner, repository.name)
    }

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

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            FloatingActionButton(
                modifier = Modifier.padding(
                    DefaultLargePadding
                ),
                onClick = { onBackPress() }, backgroundColor = MaterialTheme.colors.primary
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = ""
                )
            }
        }) {
        Column(modifier = Modifier.background(Color(0xFFDFDFDF))) {
            Card(
                modifier = Modifier
                    .padding(DefaultStandardPadding)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(DefaultStandardPadding),
                backgroundColor = MaterialTheme.colors.surface,
            ) {
                Column(
                    modifier = Modifier
                        .padding(DefaultLargePadding),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Spacer(modifier = Modifier.height(DefaultStandardPadding))
                    IconText(R.drawable.ic_git, repository.name)
                    Spacer(modifier = Modifier.height(DefaultStandardPadding))
                    IconText(R.drawable.ic_git, repository.url)
                    Spacer(modifier = Modifier.height(DefaultStandardPadding))
                    IconText(R.drawable.ic_person, repository.owner)
                    Spacer(modifier = Modifier.height(DefaultStandardPadding))
                    IconText(R.drawable.ic_person, repository.ownerUrl)
                }
            }

            LazyColumn {
                items(items = state.value.eventsList) { event ->
                    EventItem(event)
                }
            }

        }
    }


}