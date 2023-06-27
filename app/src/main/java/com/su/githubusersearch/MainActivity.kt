package com.su.githubusersearch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.su.githubusersearch.data.model.GithubUserUiModel
import com.su.githubusersearch.data.model.UserDetailUiModel
import com.su.githubusersearch.navigation.GithubUserSearchAppNavHost
import com.su.githubusersearch.ui.theme.GithubUserSearchTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieApp()
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieApp() {
    GithubUserSearchTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
            val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)

            val scope = rememberCoroutineScope()
            var githubUserDetail by remember { mutableStateOf(UserDetailUiModel()) }

            BottomSheetScaffold(
                scaffoldState = scaffoldState, sheetContent = {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(githubUserDetail.avatarurl)
                                .build(),
                            contentDescription = stringResource(R.string.github_user),
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(64.dp)
                                .clip(CircleShape)
                        )
                        Text(text = githubUserDetail.name)
                        Row(modifier = Modifier.padding(horizontal = 16.dp)) {
                            Text(
                                text = "${githubUserDetail.publicrepos} repos",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1F)
                            )
                            Text(
                                text = "${githubUserDetail.followers}followers",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1F)
                            )
                            Text(
                                text = "${githubUserDetail.following}following",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1F)
                            )
                        }
                        Text(text = githubUserDetail.bio)
                    }
                },
                sheetPeekHeight = 0.dp
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    val navController: NavHostController = rememberNavController()
                    GithubUserSearchAppNavHost(navController = navController,
                        showBottomSheet = {
                            val previousUser = githubUserDetail
                            githubUserDetail = it
                            scope.launch {
                                if (sheetState.isCollapsed) {
                                    sheetState.expand()
                                } else {
                                    if (previousUser.name == it.name)
                                        sheetState.collapse()
                                }
                            }
                        })

                }
            }
        }

    }
}