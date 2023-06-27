package com.su.githubusersearch.data.model

import com.su.githubusersearch.network.model.GithubUser
import com.su.githubusersearch.network.model.GithubUserDetailResponse

fun GithubUser.asUiModel() = GithubUserUiModel(id = id, name = login, avatarUrl = avatar_url, url=url)

fun GithubUserDetailResponse.asUiModel() = UserDetailUiModel(
    id = id,
    name = name,
    avatarurl = avatar_url,
    bio = bio ?: "",
    publicrepos = public_repos,
    publicgists = public_gists,
    followers = followers,
    following = following
)