package me.ahch.repository_list_data.mapper

import me.ahch.core.model.Repository
import me.ahch.repository_list_data.remote.dto.RepositoryDto

fun RepositoryDto.toRepository(): Repository = Repository(
    name = name,
    owner = owner.login,
    ownerUrl = owner.url,
    forks = forks,
    watchers = watchers,
    url = url
)
