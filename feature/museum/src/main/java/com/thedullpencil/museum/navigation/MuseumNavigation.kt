package com.thedullpencil.museum.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.thedullpencil.core.navigation.TopLevelRoute.Museum
import com.thedullpencil.museum.MuseumScreen

fun NavController.navigateToMuseum(navOptions: NavOptions? = null) = navigate(Museum, navOptions)

fun NavGraphBuilder.museumScreen() = composable<Museum> { MuseumScreen() }
