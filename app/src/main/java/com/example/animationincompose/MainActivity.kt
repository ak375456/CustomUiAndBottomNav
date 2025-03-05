package com.example.animationincompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.composables.icons.lucide.Album
import com.composables.icons.lucide.House
import com.composables.icons.lucide.Lucide
import com.composables.icons.lucide.PersonStanding
import com.composables.icons.lucide.Settings2
import com.composables.icons.lucide.Zap
import com.example.animationincompose.presentation.bottom_navigation.BottomNavigation
import com.example.animationincompose.ui.theme.AnimationInComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnimationInComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                    ) {
                        val bottomNavigationItem = listOf(
                            BottomNavigation(Lucide.House, "Home", "Home"),
                            BottomNavigation(Lucide.Settings2, "Setting", "Setting"),
                            BottomNavigation(Lucide.PersonStanding, "Profile", "Profile"),
                            BottomNavigation(Lucide.Album, "Album", "Album"),
                            BottomNavigation(Lucide.Zap, "Zap", "Zap"),
                        )

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(70.dp)
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
                                bottomNavigationItem.forEach {
                                    Icon(
                                        imageVector = it.icon,
                                        contentDescription = it.iconDescription,
                                        modifier =  Modifier.clickable(
                                            indication = rememberRipple(
                                                bounded = false,
                                                radius = 25.dp,
                                            ),
                                            interactionSource = remember { MutableInteractionSource() }
                                        ){
                                            Log.d("Clicked on",it.label)
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

