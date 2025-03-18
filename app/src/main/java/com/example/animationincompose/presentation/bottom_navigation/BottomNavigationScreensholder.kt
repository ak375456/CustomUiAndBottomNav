package com.example.animationincompose.presentation.bottom_navigation

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.getValue
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.composables.icons.lucide.Album
import com.composables.icons.lucide.House
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.Zap
import com.example.animationincompose.presentation.bottom_navigation.screens.Screens
import com.example.animationincompose.presentation.bottom_navigation.screens.SetupNavGraph
import androidx.compose.runtime.LaunchedEffect
import com.composables.icons.lucide.Database
import com.composables.icons.lucide.Network

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationScreenHolder(navController: NavHostController) {
    val bottomNavigationItem = listOf(
        BottomNavigation(Lucide.House, Screens.Home.route),
        BottomNavigation(Lucide.Network, Screens.Setting.route),
        BottomNavigation(Lucide.Database, Screens.Profile.route),
        BottomNavigation(Lucide.Album, Screens.Album.route),
        BottomNavigation(Lucide.Zap, Screens.Zap.route),
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val snackState = remember { SnackbarHostState() }

    LaunchedEffect(currentRoute) {
        if (currentRoute != null) {
            snackState.showSnackbar(
                "Your current route is $currentRoute",
            )
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),

        floatingActionButton = {
            if (currentRoute == Screens.Home.route){

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                        .wrapContentSize(Alignment.BottomEnd)
                ) {
                    // Additional buttons above the FAB
                    Column(
                        modifier = Modifier
                            .offset(y = (-60).dp) // Move buttons upward
                    ) {
                        // Button 1
                        FloatingActionButton(
                            onClick = { /* Handle click */ },
                            modifier = Modifier.size(48.dp),
                            containerColor = MaterialTheme.colorScheme.secondary
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Button 1",
                                tint = Color.White
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        // Button 2
                        FloatingActionButton(
                            onClick = { /* Handle click */ },
                            modifier = Modifier.size(48.dp),
                            containerColor = MaterialTheme.colorScheme.tertiary
                        ) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "Button 2",
                                tint = Color.White
                            )
                        }
                    }

                    // Main FAB
                    FloatingActionButton(
                        onClick = { /* Handle click */ },
                        modifier = Modifier.align(Alignment.BottomEnd),
                        containerColor = MaterialTheme.colorScheme.primary
                    ) {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Main FAB",
                            tint = Color.White
                        )
                    }
                }
            }
        },
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                title = {
                    Text(
                        text = currentRoute?.uppercase().toString(),
                        color = Color.White
                    )
                },
            )
        },
        //Uncomment this for toast
//        snackbarHost = {
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//            ) {
//                SnackbarHost(hostState = snackState) { data ->
//                    // Custom Snackbar with a dismiss button
//                    Snackbar(
//                        modifier = Modifier.padding(8.dp),
//                        containerColor = MaterialTheme.colorScheme.primary,
//                        contentColor = MaterialTheme.colorScheme.onPrimary,
//                        action = {
//                            // Dismiss button
//                            TextButton(
//                                onClick = { data.dismiss() }
//                            ) {
//                                Text(
//                                    text = "Dismiss",
//                                    color = MaterialTheme.colorScheme.onPrimary
//                                )
//                            }
//                        }
//                    ) {
//                        Text(data.visuals.message)
//                    }
//                }
//            }
//        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .offset(y = (-24).dp)
                    .padding(8.dp)
                    .background(
                        MaterialTheme.colorScheme.primaryContainer,
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
                            modifier = Modifier
                                .size(36.dp)
                                .clickable {
                                    navController.navigate(item.label) {
                                        popUpTo(navController.graph.startDestinationId) {
                                            saveState = true
                                        }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            if (currentRoute == item.label) {
                                GradientRingBox(
                                    modifier = Modifier.matchParentSize(),
                                    ringWidth = 8f,
                                ) {
                                    Icon(
                                        imageVector = item.icon,
                                        contentDescription = item.label,
                                        tint = Color(0xFFFFFFFF)
                                    )
                                }
                            } else {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.label,
                                    tint = Color(0xFFFFFFFF)
                                )
                            }
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

@Composable
fun GradientRingBox(
    modifier: Modifier = Modifier,
    ringWidth: Float = 8f,
    gradientColors: List<Color> = listOf(Color.Red, Color.Yellow, Color.Green),
    content: @Composable () -> Unit,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val canvasSize = size.minDimension
            val radius = (canvasSize / 2) - (ringWidth / 2)

            drawCircle(
                brush = Brush.sweepGradient(gradientColors),
                radius = radius,
                center = Offset(canvasSize / 2, canvasSize / 2),
                style = Stroke(width = ringWidth)
            )
        }
        content()
    }
}