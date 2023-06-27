package com.su.githubusersearch.di

import com.su.githubusersearch.data.GithubUserSearchRepository
import com.su.githubusersearch.data.GithubUserSearchRepositoryImpl
import com.su.githubusersearch.network.NetworkDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun provideGithubUserSearchRepository(networkDataSource: NetworkDataSource): GithubUserSearchRepository = GithubUserSearchRepositoryImpl(networkDataSource = networkDataSource)
}