package com.su.githubusersearch.network.fake

import com.su.githubusersearch.data.model.GithubUserUiModel
import com.su.githubusersearch.data.model.UserDetailUiModel
import com.su.githubusersearch.network.model.GithubUser
import com.su.githubusersearch.network.model.GithubUserDetailResponse
import org.intellij.lang.annotations.Language

object FakeDataSource {
    val sampleGithubUserUiModel = GithubUserUiModel(
        id = 66577,
        name = "JakeWharton",
        avatarUrl = "https://avatars.githubusercontent.com/u/66577?v=4",
        url = "https://api.github.com/users/JakeWharton"
    )

    val sampleGithubUserUiModel2 = GithubUserUiModel(
        id = 285917,
        name = "jake",
        avatarUrl = "https://avatars.githubusercontent.com/u/285917?v=4",
        url = "https://api.github.com/users/jake"
    )

    val sampleListGithubUserUiModel = listOf(sampleGithubUserUiModel,sampleGithubUserUiModel2)

    val sampleGithubUser = GithubUser(
        login = "JakeWharton",
        id = 66577,
        node_id = "MDQ6VXNlcjY2NTc3",
        avatar_url = "https://avatars.githubusercontent.com/u/66577?v=4",
        gravatar_id = "",
        url = "https://api.github.com/users/JakeWharton",
        html_url = "https://github.com/JakeWharton",
        followers_url = "https://api.github.com/users/JakeWharton/followers",
        following_url = "https://api.github.com/users/JakeWharton/following{/other_user}",
        gists_url = "https://api.github.com/users/JakeWharton/gists{/gist_id}",
        starred_url = "https://api.github.com/users/JakeWharton/starred{/owner}{/repo}",
        subscriptions_url = "https://api.github.com/users/JakeWharton/subscriptions",
        organizations_url = "https://api.github.com/users/JakeWharton/orgs",
        repos_url = "https://api.github.com/users/JakeWharton/repos",
        events_url = "https://api.github.com/users/JakeWharton/events{/privacy}",
        received_events_url = "https://api.github.com/users/JakeWharton/received_events",
        type = "User",
        site_admin = false,
        score = 1.0
    )

    val sampleUserDeailUiModel = UserDetailUiModel(
        id = 66577,
        avatarurl = "https://avatars.githubusercontent.com/u/66577?v=4",
        name = "Jake Wharton",
        bio = "",
        publicrepos = 129,
        publicgists = 54,
        following = 9,
        followers = 65584
    )

    val sampleUserDetailResponse = GithubUserDetailResponse(
        login = "JakeWharton",
        id = 66577,
        avatar_url = "https://avatars.githubusercontent.com/u/66577?v=4",
        name = "Jake Wharton",
        bio = null,
        public_gists = 54,
        public_repos = 129,
        following = 9,
        followers = 65584
    )

    @Language("JSON")
    val searchGithubUser = """
        [
    {
      "login": "JakeWharton",
      "id": 66577,
      "node_id": "MDQ6VXNlcjY2NTc3",
      "avatar_url": "https://avatars.githubusercontent.com/u/66577?v=4",
      "gravatar_id": "",
      "url": "https://api.github.com/users/JakeWharton",
      "html_url": "https://github.com/JakeWharton",
      "followers_url": "https://api.github.com/users/JakeWharton/followers",
      "following_url": "https://api.github.com/users/JakeWharton/following{/other_user}",
      "gists_url": "https://api.github.com/users/JakeWharton/gists{/gist_id}",
      "starred_url": "https://api.github.com/users/JakeWharton/starred{/owner}{/repo}",
      "subscriptions_url": "https://api.github.com/users/JakeWharton/subscriptions",
      "organizations_url": "https://api.github.com/users/JakeWharton/orgs",
      "repos_url": "https://api.github.com/users/JakeWharton/repos",
      "events_url": "https://api.github.com/users/JakeWharton/events{/privacy}",
      "received_events_url": "https://api.github.com/users/JakeWharton/received_events",
      "type": "User",
      "site_admin": false,
      "score": 1.0
    },
    {
      "login": "jake",
      "id": 285917,
      "node_id": "MDQ6VXNlcjI4NTkxNw==",
      "avatar_url": "https://avatars.githubusercontent.com/u/285917?v=4",
      "gravatar_id": "",
      "url": "https://api.github.com/users/jake",
      "html_url": "https://github.com/jake",
      "followers_url": "https://api.github.com/users/jake/followers",
      "following_url": "https://api.github.com/users/jake/following{/other_user}",
      "gists_url": "https://api.github.com/users/jake/gists{/gist_id}",
      "starred_url": "https://api.github.com/users/jake/starred{/owner}{/repo}",
      "subscriptions_url": "https://api.github.com/users/jake/subscriptions",
      "organizations_url": "https://api.github.com/users/jake/orgs",
      "repos_url": "https://api.github.com/users/jake/repos",
      "events_url": "https://api.github.com/users/jake/events{/privacy}",
      "received_events_url": "https://api.github.com/users/jake/received_events",
      "type": "User",
      "site_admin": false,
      "score": 1.0
    }
  ]""".trimIndent()

    @Language("JSON")
    val getUserDetail = """
        {
  "login": "JakeWharton",
  "id": 66577,
  "avatar_url": "https://avatars.githubusercontent.com/u/66577?v=4",
  "url": "https://api.github.com/users/JakeWharton",
  "name": "Jake Wharton",
  "bio": null,
  "public_repos": 129,
  "public_gists": 54,
  "followers": 65584,
  "following": 9
}""".trimIndent()
}