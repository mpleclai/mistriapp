package com.thedullpencil.home.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.thedullpencil.core.navigation.TopLevelRoute.Home
import com.thedullpencil.home.HomeScreen

fun NavController.navigateToHome(navOptions: NavOptions? = null) = navigate(Home, navOptions)

fun NavGraphBuilder.homeScreen() = composable<Home> { HomeRoute() }

@Composable
fun HomeRoute() = HomeScreen(hiltViewModel())
