
package com.example.wofview.ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

data class Feeder(val name: String, val location: String)

@Composable
fun FeedersMenuScreen(
    feeders: List<Feeder>,
    onAddFeeder: () -> Unit,
    onViewFeeder: (Feeder) -> Unit,
    onSettings: () -> Unit,
    navState: MutableState<String>? = null,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // --- Menú superior ---
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Menú de Alimentadores",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (navState != null) {
                        navState.value = "addFeeder"
                    } else {
                        onAddFeeder()
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Agregar Alimentador")
            }
            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = onSettings,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Configuración")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // --- Lista de alimentadores ---
        Text(
            text = "Alimentadores existentes:",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(feeders) { feeder ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(feeder.name, style = MaterialTheme.typography.titleMedium)
                            Text(feeder.location, style = MaterialTheme.typography.bodyMedium)
                        }
                        Button(
                            onClick = { onViewFeeder(feeder) },
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text("Ver")
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewFeedersMenuScreen() {
    val sampleFeeders = remember {
        listOf(
            Feeder("Alimentador 1", "Sala"),
            Feeder("Alimentador 2", "Cocina"),
            Feeder("Alimentador 3", "Patio")
        )
    }

    MaterialTheme {
        FeedersMenuScreen(
            feeders = sampleFeeders,
            onAddFeeder = {},
            onViewFeeder = {},
            onSettings = {}
        )
    }
}
