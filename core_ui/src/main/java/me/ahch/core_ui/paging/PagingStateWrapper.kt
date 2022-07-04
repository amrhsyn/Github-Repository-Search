package me.ahch.core_ui.paging
sealed class PagingStateWrapper {
    object Loading : PagingStateWrapper()
    object Finished : PagingStateWrapper()
    data class Failure(var message: String) : PagingStateWrapper()
    data class None(var refresh: Boolean) : PagingStateWrapper()
}

