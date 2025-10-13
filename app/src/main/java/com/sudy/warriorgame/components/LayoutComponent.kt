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
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.sudy.warriorgame.warriors.viewmodels.UnitListViewModel


@Composable
fun ResponsiveLayout(modifier: Modifier = Modifier, vm: UnitListViewModel) =
    when (LocalConfiguration.current.orientation) {
        Configuration.ORIENTATION_PORTRAIT -> PortraitLayout(
            modifier = modifier, vm = vm
        )

        else -> LandscapeLayout(modifier = modifier, vm = vm)
    }


@Composable
fun PortraitLayout(modifier: Modifier = Modifier, vm: UnitListViewModel ) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        ArmyGrid(vm)
    }
}

@Composable
fun LandscapeLayout(modifier: Modifier = Modifier, vm: UnitListViewModel) {
    Row(
        modifier = modifier.fillMaxSize(),
        verticalAlignment = Alignment.Top
    ) {
        ArmyGrid(vm)
    }
}