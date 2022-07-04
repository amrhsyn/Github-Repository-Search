package me.ahch.core.model

data class Repository(
    val name: String,
    val owner: String,
    val ownerUrl: String,
    val forks: Int,
    val watchers: Int,
    val url: String,
)
