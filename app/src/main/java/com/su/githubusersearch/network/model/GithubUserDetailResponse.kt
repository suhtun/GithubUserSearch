package com.su.githubusersearch.network.model

import com.squareup.moshi.Json
import kotlinx.serialization.Serializable

@Serializable
data class GithubUserDetailResponse(
    @Json val login: String,
    @Json val id: Int,
    @Json val avatar_url: String,
    @Json val name: String,
    @Json val bio: String?,
    @Json val public_repos: Int,
    @Json val public_gists: Int,
    @Json val followers: Int,
    @Json val following: Int,
)
