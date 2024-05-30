package com.telema.malakisi.domain.ui

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.vector.ImageVector
import com.telema.malakisi.R

sealed class NavigationScreen(val route: String, @StringRes val resourceId: Int, val icon : ImageVector) {
    data object HomeMenuItem : NavigationScreen("home", R.string.home, Icons.Filled.Home)
    data object InfoMenuItem : NavigationScreen("info", R.string.info, Icons.Filled.Info)
}