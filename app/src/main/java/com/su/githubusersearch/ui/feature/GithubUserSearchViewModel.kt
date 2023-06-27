package com.su.githubusersearch.ui.feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.su.githubusersearch.data.GithubUserSearchRepository
import com.su.githubusersearch.data.model.GithubUserUiModel
import com.su.githubusersearch.data.model.UserDetailUiModel
import com.su.githubusersearch.util.asResult
import com.su.githubusersearch.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubUserSearchViewModel @Inject constructor(val repo: GithubUserSearchRepository) :
    ViewModel() {
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _githubUserSearch = MutableStateFlow(GithubSearchScreenUiState("",SeachUserUiState.Default))
    val githubUserSearch = _githubUserSearch.asStateFlow()

    private val _userDetail = Channel<UserDetailUiModel>()
    val userDetail = _userDetail.receiveAsFlow()


    fun onSearchTextChange(username: String) {
        _searchText.value = username
        viewModelScope.launch {
            repo.findGithubUser(username).asResult().collect { result ->
                _githubUserSearch.update {
                    when (result) {
                        is Result.Loading -> {
                            GithubSearchScreenUiState(username,SeachUserUiState.Loading)
                        }

                        is Result.Success -> {
                            GithubSearchScreenUiState(username,SeachUserUiState.Success(result.data))
                        }

                        is Result.Error -> {
                            GithubSearchScreenUiState(username,SeachUserUiState.Error)
                        }
                    }
                }
            }
        }
    }

    fun getUserDetail(userurl: String) {
        viewModelScope.launch {
            repo.getGithubUserDetail(userurl).asResult().collect { result ->

                when (result) {
                    is Result.Loading -> {}

                    is Result.Success -> {
                        _userDetail.send(result.data)
                    }

                    is Result.Error -> {}
                }
            }
        }
    }
}

sealed interface SeachUserUiState{
    data class Success( val githubUsers: List<GithubUserUiModel> = emptyList()): SeachUserUiState
    object Loading: SeachUserUiState
    object Default: SeachUserUiState
    object Error: SeachUserUiState
}

data class GithubSearchScreenUiState(val searchText: String,val searchUserUiState: SeachUserUiState)