package com.su.githubusersearch.data

import com.su.githubusersearch.data.model.GithubUserUiModel
import com.su.githubusersearch.data.model.UserDetailUiModel
import kotlinx.coroutines.flow.Flow

interface GithubUserSearchRepository {

    fun findGithubUser(username: String): Flow<List<GithubUserUiModel>>

    fun getGithubUserDetail(userUrl:String): Flow<UserDetailUiModel>
}