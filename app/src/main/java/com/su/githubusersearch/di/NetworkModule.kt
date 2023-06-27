package com.su.githubusersearch.di

import com.su.githubusersearch.network.NetworkDataSource
import com.su.githubusersearch.network.retrofit.GithubUserSearchApi
import com.su.githubusersearch.network.retrofit.RetrofitGithubSearchUserApiFactory
import com.su.githubusersearch.network.retrofit.RetrofitNetworkDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideMovieApi(): GithubUserSearchApi {
        return RetrofitGithubSearchUserApiFactory.githubUserSearchApi
    }

    @Singleton
    @Provides
    fun provideNetworkDataSource(githubUserSearchApi: GithubUserSearchApi): NetworkDataSource =
        RetrofitNetworkDataSource(githubUserSearchApi)

}