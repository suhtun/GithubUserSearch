package com.su.githubusersearch.network

import com.su.githubusersearch.network.fake.FakeDataSource
import com.su.githubusersearch.network.fake.FakeNetworkDataSource
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals
class FakeNetworkDataSourceTest {

    private lateinit var subject: FakeNetworkDataSource

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        subject = FakeNetworkDataSource(
            ioDispatcher = testDispatcher,
            networkJson = Json { ignoreUnknownKeys = true }
        )
    }

    @Test
    fun testDeserializationOfGithubSearchUser() = runTest(testDispatcher) {
        assertEquals(
            FakeDataSource.sampleGithubUser,
            subject.findGithubUser("jake").first()
        )
    }

    @Test
    fun testDeserializationOfUserDetail() = runTest(testDispatcher) {
        assertEquals(FakeDataSource.sampleUserDetailResponse, subject.getGithubUserDetail("https://api.github.com/users/JakeWharton"))
    }

}