package com.sudy.warriorgame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.sudy.warriorgame.components.ResponsiveLayout
import com.sudy.warriorgame.ui.theme.AppTheme
import com.sudy.warriorgame.warriors.viewmodels.UnitListViewModel
import dagger.hilt.android.AndroidEntryPoint

//Starting point of the app, gathering all the components together
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val vm: UnitListViewModel = hiltViewModel()
            AppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = MaterialTheme.colorScheme.background,

                    ) { innerPadding ->
                    Surface(
                        color = MaterialTheme.colorScheme.surface,
                        modifier = Modifier
                            .padding(innerPadding)

                    ) {

                            ResponsiveLayout(vm = vm)
                    }
                }
            }
        }
    }
}



