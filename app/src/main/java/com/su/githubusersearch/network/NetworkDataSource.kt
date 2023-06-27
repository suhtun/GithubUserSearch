package com.su.githubusersearch.network

import com.su.githubusersearch.network.model.GithubUser
import com.su.githubusersearch.network.model.GithubUserDetailResponse

interface NetworkDataSource {

    suspend fun findGithubUser(username: String): List<GithubUser>

    suspend fun getGithubUserDetail(userUrl:String): GithubUserDetailResponse
}