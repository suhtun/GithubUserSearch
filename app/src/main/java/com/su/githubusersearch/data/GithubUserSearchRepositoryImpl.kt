package com.su.githubusersearch.data

import com.su.githubusersearch.data.model.GithubUserUiModel
import com.su.githubusersearch.data.model.UserDetailUiModel
import com.su.githubusersearch.data.model.asUiModel
import com.su.githubusersearch.network.NetworkDataSource
import com.su.githubusersearch.network.model.GithubUser
import com.su.githubusersearch.network.model.GithubUserDetailResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class GithubUserSearchRepositoryImpl @Inject constructor(val networkDataSource: NetworkDataSource) :
    GithubUserSearchRepository {
    override fun findGithubUser(username: String): Flow<List<GithubUserUiModel>> = flow {
        if (username.isEmpty()) emit(emptyList())
        val response = networkDataSource.findGithubUser(username)
        emit(response.map(GithubUser::asUiModel))
    }.flowOn(Dispatchers.IO)

    override fun getGithubUserDetail(userUrl: String): Flow<UserDetailUiModel> = flow {
        val response = networkDataSource.getGithubUserDetail(userUrl)
        emit(response.let(GithubUserDetailResponse::asUiModel))
    }.flowOn(Dispatchers.IO)

}