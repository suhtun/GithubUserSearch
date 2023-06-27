package com.su.githubusersearch

import app.cash.turbine.test
import com.su.githubusersearch.data.TestGithubUserSearchRepository
import com.su.githubusersearch.ui.feature.GithubUserSearchViewModel
import com.su.githubusersearch.ui.feature.SeachUserUiState
import com.su.githubusersearch.util.TestDispatcherRule
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GithubUserSearchViewModelTest {

    private val repository = TestGithubUserSearchRepository()
    private lateinit var viewModel :GithubUserSearchViewModel

    @get:Rule
    val dispatcherRule = TestDispatcherRule()
    
    @Before
    fun setUp() {
        viewModel = GithubUserSearchViewModel(repo = repository)
        repository.sendSearchUserAndUserDetail()
        viewModel.onSearchTextChange("jake")
        viewModel.getUserDetail("")
    }

    @Test
    fun `github user search ui state when success matches find github user method from repository`() = runTest {
        viewModel.githubUserSearch.test {
            val item = awaitItem()
            assertTrue(item.searchUserUiState is SeachUserUiState.Success)

            val githubUserSearchFromRepository = repository.findGithubUser("jake").first()
            val githubUserSearchUiState = item.searchUserUiState as SeachUserUiState.Success

            assertEquals(githubUserSearchFromRepository,githubUserSearchUiState.githubUsers)
        }
    }

    @Test
    fun `user detail ui state when success matches find user detail method from repository`() = runTest {
        viewModel.userDetail.test {
            val item = awaitItem()
            val userDetailFromRepository = repository.getGithubUserDetail("").first()

            assertEquals(userDetailFromRepository,item)
        }
    }
    
}