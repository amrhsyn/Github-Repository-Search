package me.ahch.githubSearch

import android.annotation.SuppressLint
import android.net.Uri
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.InternalCoroutinesApi
import me.ahch.core.model.Repository
import me.ahch.githubSearch.navigation.Argument.REPOSITORY_ARGUMENT
import me.ahch.githubSearch.navigation.Route
import me.ahch.githubSearch.ui.theme.GithubSearchTheme
import me.ahch.repository_details_presentation.DetailsScreen
import me.ahch.repository_list_presentation.SearchScreen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@InternalCoroutinesApi
@Composable
fun GithubSearchApp() {

    GithubSearchTheme {

        val navController = rememberNavController()
        val scaffoldState = rememberScaffoldState()

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            scaffoldState = scaffoldState,
        ) {
            NavHost(
                navController = navController,
                startDestination = Route.SEARCH_SCREEN
            ) {
                composable(
                    route = Route.SEARCH_SCREEN
                ) {
                    SearchScreen(
                        scaffoldState = scaffoldState,
                        viewModel = hiltViewModel(),
                        navigateToDetailsScreen = {
                            val jsonRepository = Uri.encode(Gson().toJson(it))
                            navController.navigate(Route.DETAILS_SCREEN + "/${jsonRepository}")
                        }
                    )
                }
                composable(
                    route = Route.DETAILS_SCREEN + "/{$REPOSITORY_ARGUMENT}",
                    arguments = listOf(
                        navArgument(REPOSITORY_ARGUMENT) {
                            type = NavType.StringType
                        }
                    )
                ) {
                    val repositoryType = object : TypeToken<Repository>() {}.type
                    val selectedRepository = Gson().fromJson<Repository>(
                        it.arguments?.getString(REPOSITORY_ARGUMENT)!!,
                        repositoryType
                    )
                    DetailsScreen(
                        scaffoldState = scaffoldState,
                        viewModel = hiltViewModel(),
                        selectedRepository,
                        onBackPress = {
                            navController.navigateUp()
                        }
                    )
                }
            }
        }
    }
}

