package com.sudy.warriorgame

import android.content.res.Resources.Theme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sudy.warriorgame.components.ResponsiveLayout
import com.sudy.warriorgame.components.UnitDropDownComponent
import com.sudy.warriorgame.components.UnitType
import com.sudy.warriorgame.ui.theme.AppTheme
import com.sudy.warriorgame.ui.theme.primaryLight
import com.sudy.warriorgame.ui.theme.surfaceLight


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
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
                            ResponsiveLayout()
                    }
                }
            }
        }
    }
}



