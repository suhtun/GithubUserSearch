package com.su.githubusersearch.data

import com.su.githubusersearch.network.fake.FakeDataSource
import com.su.githubusersearch.network.fake.FakeNetworkDataSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GithubUserSearchRepositoryTest {

    private lateinit var networkDataSource: FakeNetworkDataSource
    private lateinit var repository: GithubUserSearchRepository

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        networkDataSource = FakeNetworkDataSource(
            ioDispatcher = testDispatcher,
            networkJson = Json { ignoreUnknownKeys = true }
        )
        repository = GithubUserSearchRepositoryImpl(networkDataSource)
    }

    @Test
    fun `fetch github user with search user name keyword is backed by network`() =
        runTest(testDispatcher) {
            Assert.assertEquals(
                FakeDataSource.sampleListGithubUserUiModel,
                repository.findGithubUser("jake").first()
            )
        }

    @Test
    fun `search Github user by userurl is backed by network`() = runTest(testDispatcher) {
        val expectedData = FakeDataSource.sampleUserDeailUiModel
        val actualData = repository.getGithubUserDetail("https://api.github.com/users/JakeWharton").first()
        Assert.assertEquals(expectedData.id, actualData.id)
        Assert.assertEquals(expectedData.name, actualData.name)
        Assert.assertEquals(expectedData.avatarurl, actualData.avatarurl)
        Assert.assertEquals(expectedData.followers, actualData.followers)
        Assert.assertEquals(expectedData.following, actualData.following)
    }

}