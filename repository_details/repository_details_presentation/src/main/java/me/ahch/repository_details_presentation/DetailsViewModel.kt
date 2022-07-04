package me.ahch.repository_details_presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import me.ahch.core.utils.Resource
import me.ahch.core_ui.utils.UiEvent
import me.ahch.core_ui.utils.UiText
import me.ahch.repository_details_domain.usecase.GetEventsUseCase
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject
constructor(private val getEventsUseCase: GetEventsUseCase) : ViewModel() {

    private var _state = MutableStateFlow(DetailsState())
    val state = _state


    private var job: Job? = null

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()


    fun getEvents(ownerName: String, repositoryName: String) {
        viewModelScope.launch {
            when (val result = getEventsUseCase.invoke(ownerName, repositoryName)) {
                is Resource.Success -> {
                    _state.value = state.value.copy(eventsList = result.data)
                }
                is Resource.Error -> {
                    _uiEvent.send(
                        UiEvent.ShowSnackbar(
                            if (result.message.isNullOrEmpty()) {
                                UiText.StringResource(R.string.details_screen_error_something_went_wrong)
                            } else {
                                UiText.DynamicString(result.message)
                            }
                        )
                    )
                }
                is Resource.Loading -> {

                }
            }
        }
    }

}