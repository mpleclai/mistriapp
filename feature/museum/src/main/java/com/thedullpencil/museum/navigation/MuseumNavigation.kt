package com.thedullpencil.museum.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.thedullpencil.museum.MuseumScreen

const val MUSEUM_ROUTE = "museum_route"

fun NavController.navigateToMuseum(navOptions: NavOptions? = null) { navigate(MUSEUM_ROUTE, navOptions) }

fun NavGraphBuilder.museumScreen() = composable(route = MUSEUM_ROUTE) { MuseumRoute() }

@Composable
fun MuseumRoute() { MuseumScreen() }