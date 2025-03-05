package com.example.animationincompose.presentation.bottom_navigation

import androidx.compose.runtime.getValue
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.composables.icons.lucide.Album
import com.composables.icons.lucide.House
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.PersonStanding
import com.composables.icons.lucide.Settings2
import com.composables.icons.lucide.Zap
import com.example.animationincompose.presentation.bottom_navigation.screens.Screens
import com.example.animationincompose.presentation.bottom_navigation.screens.SetupNavGraph

@Composable
fun BottomNavigationScreenHolder(navController: NavHostController) {
    val bottomNavigationItem = listOf(
        BottomNavigation(Lucide.House, Screens.Home.route),
        BottomNavigation(Lucide.Settings2, Screens.Setting.route),
        BottomNavigation(Lucide.PersonStanding, Screens.Profile.route),
        BottomNavigation(Lucide.Album, Screens.Album.route),
        BottomNavigation(Lucide.Zap, Screens.Zap.route),
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .offset(y = (-24).dp)
                    .padding(8.dp)
                    .background(
                        MaterialTheme
                            .colorScheme
                            .secondaryContainer,
                        shape = Shapes().medium,
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    bottomNavigationItem.forEach { item ->
                        Box(
                            modifier = if(currentRoute == item.label) {
                                Modifier
                                    .size(36.dp)
                                    .background(
                                        MaterialTheme.colorScheme.inversePrimary,
                                        shape = Shapes().medium
                                    )
                            } else {
                                Modifier
                            },

                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.label,
                                tint = Color(0xFF000000),
                                modifier = Modifier.clickable(
                                    indication = rememberRipple(
                                        bounded = false,
                                        radius = 25.dp,
                                    ),
                                    interactionSource = remember { MutableInteractionSource() }
                                ) {
                                    navController.navigate(item.label) {
                                        // Pop up to the start destination to avoid building up a large back stack
                                        popUpTo(navController.graph.startDestinationId) {
                                            saveState = true
                                        }
                                        // Avoid multiple copies of the same destination when reselecting the same item
                                        launchSingleTop = true
                                        // Restore state when reselecting a previously selected item
                                        restoreState = true
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            SetupNavGraph(navController)
        }
    }
}