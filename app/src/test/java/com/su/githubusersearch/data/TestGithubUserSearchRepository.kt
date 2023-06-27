package com.su.githubusersearch.data

import com.su.githubusersearch.data.model.GithubUserUiModel
import com.su.githubusersearch.data.model.UserDetailUiModel
import com.su.githubusersearch.network.fake.FakeDataSource
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class TestGithubUserSearchRepository: GithubUserSearchRepository {
    /**
     * The backing hot flow for the list of topics ids for testing.
     */
    private val searchUsersFlow: MutableSharedFlow<List<GithubUserUiModel>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    private val userDetailFlow: MutableSharedFlow<UserDetailUiModel> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    override fun findGithubUser(username: String): Flow<List<GithubUserUiModel>> = searchUsersFlow

    override fun getGithubUserDetail(userUrl: String): Flow<UserDetailUiModel> = userDetailFlow

    fun sendSearchUserAndUserDetail(){
        searchUsersFlow.tryEmit(FakeDataSource.sampleListGithubUserUiModel)
        userDetailFlow.tryEmit(FakeDataSource.sampleUserDeailUiModel)
    }
}