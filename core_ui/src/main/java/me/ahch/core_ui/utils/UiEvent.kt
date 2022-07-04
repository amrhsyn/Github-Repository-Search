package me.ahch.core_ui.utils

sealed class UiEvent {
    object NavigateUp: UiEvent()
    data class ShowSnackbar(val message: UiText): UiEvent()
}
