package com.su.githubusersearch.network.fake

import com.su.githubusersearch.network.NetworkDataSource
import com.su.githubusersearch.network.model.GithubUser
import com.su.githubusersearch.network.model.GithubUserDetailResponse
import com.su.githubusersearch.util.Dispatcher
import com.su.githubusersearch.util.GithubUserSearchDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import javax.inject.Inject

class FakeNetworkDataSource @Inject constructor(
    @Dispatcher(GithubUserSearchDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
    private val networkJson: Json
) : NetworkDataSource {

    override suspend fun findGithubUser(username: String): List<GithubUser> =
        withContext(ioDispatcher) {
            networkJson.decodeFromString(FakeDataSource.searchGithubUser)
        }

    override suspend fun getGithubUserDetail(userUrl: String): GithubUserDetailResponse =
        withContext(ioDispatcher) {
            networkJson.decodeFromString(FakeDataSource.getUserDetail)
        }
}