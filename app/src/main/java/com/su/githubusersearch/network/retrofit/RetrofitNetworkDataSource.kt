package com.su.githubusersearch.network.retrofit

import com.su.githubusersearch.network.model.GithubUser
import com.su.githubusersearch.network.NetworkDataSource
import com.su.githubusersearch.network.model.GithubUserDetailResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RetrofitNetworkDataSource @Inject constructor(val githubUserSearchApi: GithubUserSearchApi) :
    NetworkDataSource {
    override suspend fun findGithubUser(username: String): List<GithubUser> {
        return githubUserSearchApi.findGithubSearchUser(username).items
    }

    override suspend fun getGithubUserDetail(userUrl: String): GithubUserDetailResponse {
        return githubUserSearchApi.getGithubUserDetail(url = userUrl)
    }
}