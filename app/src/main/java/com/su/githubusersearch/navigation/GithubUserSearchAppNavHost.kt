package com.su.githubusersearch.navigation

import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.su.githubusersearch.data.model.GithubUserUiModel
import com.su.githubusersearch.data.model.UserDetailUiModel
import com.su.githubusersearch.navigation.Destinations.HOME_ROUTE
import com.su.githubusersearch.ui.feature.GithubSearchUserRoute

object Destinations {
    const val HOME_ROUTE = "github-user-search"
}

@Composable
fun GithubUserSearchAppNavHost(
    navController: NavHostController,
    showBottomSheet: (UserDetailUiModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = HOME_ROUTE,
        modifier = modifier
    ) {
        composable(HOME_ROUTE) {
            GithubSearchUserRoute(
                showDetail = { showBottomSheet(it)},
                modifier = modifier
            )
        }
    }
}
