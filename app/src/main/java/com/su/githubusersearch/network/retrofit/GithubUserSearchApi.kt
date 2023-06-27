package com.su.githubusersearch.network.retrofit

import com.su.githubusersearch.network.model.GithubSearchUserResponse
import com.su.githubusersearch.network.model.GithubUserDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface GithubUserSearchApi {
    @GET("search/users")
    suspend fun findGithubSearchUser(@Query("q") username: String): GithubSearchUserResponse


    @GET
    suspend fun getGithubUserDetail(@Url url: String): GithubUserDetailResponse

    companion object {
        const val NETWORK_API = "https://api.github.com/"
    }
}