package com.thedullpencil.mistriapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.thedullpencil.mistriapp.HomeScreen

const val HOME_ROUTE = "home_route"

fun NavController.navigateToHome(navOptions: NavOptions? = null) { navigate(HOME_ROUTE, navOptions) }

fun NavGraphBuilder.homeScreen() = composable(route = HOME_ROUTE) { HomeRoute() }

@Composable
fun HomeRoute() { HomeScreen() }