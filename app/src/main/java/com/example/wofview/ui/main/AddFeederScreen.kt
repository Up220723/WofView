
package com.example.wofview.ui.main

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun AddFeederScreen(
    onSaveFeeder: (String, String, Boolean, Int) -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier.fillMaxWidth()
) {
    var name by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var autoMode by remember { mutableStateOf(false) }
    var containerLevel by remember { mutableStateOf(0f) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Agregar alimentador",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
        )

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nombre del alimentador") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = location,
            onValueChange = { location = it },
            label = { Text("Ubicación (ej. Sala, Cocina)") },
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Modo automático")
            Switch(
                checked = autoMode,
                onCheckedChange = { autoMode = it }
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Nivel del contenedor: ${containerLevel.toInt()}%")
            Slider(
                value = containerLevel,
                onValueChange = { containerLevel = it },
                valueRange = 0f..100f
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                onSaveFeeder(name, location, autoMode, containerLevel.toInt())
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar alimentador")
        }

        TextButton(onClick = onCancel, modifier = Modifier.fillMaxWidth()) {
            Text("Cancelar")
        }
    }
}

@Preview(showBackground = true, widthDp = 360, showSystemUi = true)
@Composable
fun PreviewAddFeederScreen() {
    MaterialTheme {
        AddFeederScreen(
            onSaveFeeder = { _, _, _, _ -> },
            onCancel = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}
