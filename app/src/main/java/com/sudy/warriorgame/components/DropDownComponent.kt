package com.sudy.warriorgame.components

import androidx.compose.runtime.Composable
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sudy.warriorgame.warriors.configs.cartage
import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.IconButton


@Preview(showBackground = true, showSystemUi = false)
@Composable
fun UnitDropDownPreview() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        UnitDropDownComponent({})
    }
}

@Composable
fun UnitDropDownComponent(onCreate: (UnitType) -> Unit ) {
    var expanded by remember { mutableStateOf(false) }
    var selected by remember { mutableStateOf<UnitType>(cartage[0]) }

    Box(
        modifier = Modifier.padding(12.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer
                ),
                elevation = CardDefaults.cardElevation(6.dp),
                modifier = Modifier
                    .fillMaxWidth(0.80f)
                    .clickable { expanded = true }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),

                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = selected.name,
                        style = MaterialTheme.typography.labelLarge,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = MaterialTheme.colorScheme.tertiary
                    )
                    Icon(
                        Icons.Filled.ArrowDropDown,
                        contentDescription = "dropdown",
                        tint = MaterialTheme.colorScheme.secondary
                    )
                }
            }
            IconButton(
                onClick = { onCreate(selected) }
            ) {
                Icon(
                    Icons.Filled.Add,
                    contentDescription = "create",
                    tint = MaterialTheme.colorScheme.secondary
                )
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth(0.94f)
        ) {
            cartage.forEach { unitType ->
                if (selected != unitType)
                    DropdownMenuItem(
                        text = { Text(unitType.name) },
                        onClick = {
                            selected = unitType
                            expanded = false
                            Log.i("UnitDropDownComponent", unitType.name)
                        }
                    )
            }
        }
    }
}
