package com.su.githubusersearch.util

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val dispatcher: GithubUserSearchDispatchers)

enum class GithubUserSearchDispatchers {
    IO
}
