package com.su.githubusersearch.ui.feature

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.su.githubusersearch.R
import com.su.githubusersearch.data.model.UserDetailUiModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun GithubSearchUserRoute(
    showDetail: (UserDetailUiModel) -> Unit,
    modifier: Modifier,
    viewModel: GithubUserSearchViewModel = hiltViewModel(),
) {
    val controller = LocalSoftwareKeyboardController.current

    val searchText by viewModel.searchText.collectAsState()
    val uiState: GithubSearchScreenUiState by viewModel.githubUserSearch.collectAsState()
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    LaunchedEffect(key1 = Unit) {
        lifecycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            launch {
                viewModel.userDetail.collectLatest {
                    showDetail(it)
                }
            }
        }
    }

    GithubSearchUserScreen(
        searchText,
        uiState = uiState,
        onSearchTextChange = viewModel::onSearchTextChange,
        onClick = {
            controller?.hide()
            viewModel.getUserDetail(it)
        },
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GithubSearchUserScreen(
    searchText: String,
    uiState: GithubSearchScreenUiState,
    onSearchTextChange: (String) -> Unit,
    onClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = searchText,
            onValueChange = {
                onSearchTextChange(it)
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            placeholder = { Text(text = "Search") }
        )
        Spacer(modifier = modifier.height(16.dp))
        when (uiState.searchUserUiState) {
            SeachUserUiState.Default -> {}
            SeachUserUiState.Loading -> {
                Box(modifier = modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }

            is SeachUserUiState.Success -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1F)
                ) {
                    val githubUsers = uiState.searchUserUiState.githubUsers
                    items(githubUsers.size) {
                        val githubUser = githubUsers[it]
                        Row(
                            modifier = modifier
                                .padding(vertical = 8.dp)
                                .clickable { onClick(githubUser.url) },
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(githubUser.avatarUrl)
                                    .build(),
                                contentDescription = stringResource(R.string.github_user),
                                contentScale = ContentScale.Crop,
                                modifier = modifier
                                    .size(64.dp)
                                    .clip(CircleShape)
                            )
                            Text(
                                text = githubUser.name,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp)
                            )
                        }

                    }
                }
            }

            SeachUserUiState.Error -> {}
        }
    }
}

