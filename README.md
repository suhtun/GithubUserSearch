Modern Movie App with Android Architecture Components
===========================================================
| Github User Search                                    | User Detail                                   |
| ----------------------------------------------------- | --------------------------------------------- |
| ![home](https://github.com/suhtun/GithubUserSearch/blob/main/githubsearchuser.png) | ![detail](https://github.com/suhtun/GithubUserSearch/blob/main/userdetail.png) |

Github User Search App => designed and built in the  architecture. App built entirely with Kotlin and Jetpack Compose that follows Android design and development best practices. 
This is the repository for the GitHub User Search app. 
Introduction
-------------

### Feature
Github User Search able to search Github users by![docs.github.com](https://docs.github.com/en/rest/search?apiVersion=2022-11-28#search-users). Users can even browse each user's detail such as public repositories, followers, and following.

### Architecture
Modern Movie follows the Google official architecture guidance.
### Modularization
Modern Movie app has been fully modularized and followed Google's official modularization learning journey.
### Testing
fully covered for unit test and instrumentation test.

### Tech stacks 
* Minimum SDK level 24
* Kotlin based, Coroutines + Flow for asynchronous.
* Jetpack Compose: User Interface
* StateFlow: handles UI states upon the lifecycle changes.
* ViewModel: Manages UI-related data holder and lifecycle awareness. Allows data to survive configuration changes such as screen rotations.
* Room: Constructs Database by providing an abstraction layer over SQLite to allow fluent database access.
* Hilt: for dependency injection.
* Architecture
* MVVM Architecture (Model - View - ViewModel)
* Repository Pattern
* Retrofit2: the REST APIs.
* Moshi: A modern JSON library for Kotlin and Java.
* Material-Components: Material design components.
