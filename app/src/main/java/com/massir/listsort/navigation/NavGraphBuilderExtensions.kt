package com.massir.listsort.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.massir.listsort.list.ListScreen

fun NavGraphBuilder.addMainGraphTopLevel(
    navController: NavController
) {
    navigation<MainGraph>(
        startDestination = List,
    ) {
        composable<List> {
            ListScreen(navController = navController)
        }
    }
}