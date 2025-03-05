package com.example.animationincompose.presentation.bottom_navigation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
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

@Composable
fun BottomNavigationScreenHolder() {
    val bottomNavigationItem = listOf(
        BottomNavigation(Lucide.House, "Home"),
        BottomNavigation(Lucide.Settings2, "Setting"),
        BottomNavigation(Lucide.PersonStanding, "Profile"),
        BottomNavigation(Lucide.Album, "Album"),
        BottomNavigation(Lucide.Zap, "Zap"),
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
                    contentDescription = it.label,
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