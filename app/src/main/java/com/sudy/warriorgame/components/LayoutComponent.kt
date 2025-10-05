package com.sudy.warriorgame.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration


@Composable
fun ResponsiveLayout(modifier: Modifier = Modifier) =
    when (LocalConfiguration.current.orientation) {
        Configuration.ORIENTATION_PORTRAIT -> PortraitLayout(
            modifier = modifier,
        )

        else -> LandscapeLayout(modifier = modifier)
    }


@Composable
fun PortraitLayout(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        ArmyGrid()
    }
}

@Composable
fun LandscapeLayout(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxSize(),
        verticalAlignment = Alignment.Top
    ) {
            ArmyGrid()
    }
}