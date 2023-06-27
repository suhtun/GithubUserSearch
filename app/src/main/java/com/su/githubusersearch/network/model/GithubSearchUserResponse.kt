package com.su.githubusersearch.network.model

import com.squareup.moshi.Json
import kotlinx.serialization.Serializable

data class GithubSearchUserResponse(
    @Json(name = "total_count") val total_count: Int,
    @Json(name = "incomplete_results") val incomplete_results: Boolean,
    @Json(name = "items") val items: List<GithubUser>
)

@Serializable
data class GithubUser(
    @Json val login: String,
    @Json val id: Int,
    @Json val node_id: String,
    @Json val avatar_url: String,
    @Json val gravatar_id: String,
    @Json val url: String,
    @Json val html_url: String,
    @Json val followers_url: String,
    @Json val following_url: String,
    @Json val gists_url: String,
    @Json val starred_url: String,
    @Json val subscriptions_url: String,
    @Json val organizations_url: String,
    @Json val repos_url: String,
    @Json val events_url: String,
    @Json val received_events_url: String,
    @Json val type: String,
    @Json val site_admin: Boolean,
    @Json val score: Double,
)