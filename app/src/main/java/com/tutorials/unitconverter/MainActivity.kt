package com.tutorials.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tutorials.unitconverter.ui.theme.UnitConverterTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter() {
    var inputValue = remember { mutableStateOf("") }
    var outputValue = remember { mutableStateOf("") }
    var inputUnit = remember { mutableStateOf("Meters") }
    var outputUnit = remember { mutableStateOf("Feet") }
    var isInputDropdownExpanded = remember { mutableStateOf(false) }
    var isOutputDropdownExpanded = remember { mutableStateOf(false) }
    val conversionFactor = remember { mutableDoubleStateOf(1.00) }
    val oConversionFactor = remember {mutableDoubleStateOf(0.3048)}

    fun convertUnits() {
        val inputValueDouble = inputValue.value.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversionFactor.doubleValue * 100.0 / oConversionFactor.doubleValue).roundToInt() / 100.0
        outputValue.value = result.toString()
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title at the top
        Text(
            text = "Unit Converter",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Input field
        OutlinedTextField( value = inputValue.value, onValueChange = {
            inputValue.value = it
            convertUnits()
            },
            label = { Text("Enter Value") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            // First Dropdown
            Box {
                Button(onClick = { isInputDropdownExpanded.value = !isInputDropdownExpanded.value }) {
                    Text(text = if (inputUnit.value == "") {
                        "Select"
                    } else {
                        inputUnit.value
                    })
                    Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                }
                DropdownMenu(
                    expanded = isInputDropdownExpanded.value,
                    onDismissRequest = { isInputDropdownExpanded.value = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Centimeters") },
                        onClick = {
                            inputUnit.value = "Centimeters"
                            isInputDropdownExpanded.value = false
                            conversionFactor.doubleValue = 0.01
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Meters") },
                        onClick = {
                            inputUnit.value = "Meters"
                            isInputDropdownExpanded.value = false
                            conversionFactor.doubleValue = 1.0
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Feet") },
                        onClick = {
                            inputUnit.value = "Feet"
                            isInputDropdownExpanded.value = false
                            conversionFactor.doubleValue = 0.3048
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Millimeters") },
                        onClick = {
                            inputUnit.value = "Millimeters"
                            isInputDropdownExpanded.value = false
                            conversionFactor.doubleValue = 0.001
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Kilometers") },
                        onClick = {
                            inputUnit.value = "Kilometers"
                            isInputDropdownExpanded.value = false
                            conversionFactor.doubleValue = 1000.0
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Inches") },
                        onClick = {
                            inputUnit.value = "Inches"
                            isInputDropdownExpanded.value = false
                            conversionFactor.doubleValue = 0.0254
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Yards") },
                        onClick = {
                            inputUnit.value = "Yards"
                            isInputDropdownExpanded.value = false
                            conversionFactor.doubleValue = 0.9144
                            convertUnits()
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Second Dropdown
            Box {
                Button(onClick = { isOutputDropdownExpanded.value = true }) {
                    Text(outputUnit.value)
                    Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                }
                DropdownMenu(
                    expanded = isOutputDropdownExpanded.value,
                    onDismissRequest = { isOutputDropdownExpanded.value = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Centimeters") },
                        onClick = {
                            isOutputDropdownExpanded.value = false
                            outputUnit.value = "Centimeters"
                            oConversionFactor.doubleValue = 0.01
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Meters") },
                        onClick = {
                            isOutputDropdownExpanded.value = false
                            outputUnit.value = "Meters"
                            oConversionFactor.doubleValue = 1.0
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Feet") },
                        onClick = {
                            isOutputDropdownExpanded.value = false
                            outputUnit.value = "Feet"
                            oConversionFactor.doubleValue = 0.3048
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Millimeters") },
                        onClick = {
                            isOutputDropdownExpanded.value = false
                            outputUnit.value = "Millimeters"
                            oConversionFactor.doubleValue = 0.001
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Kilometers") },
                        onClick = {
                            isOutputDropdownExpanded.value = false
                            outputUnit.value = "Kilometers"
                            oConversionFactor.doubleValue = 1000.0
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Inches") },
                        onClick = {
                            isOutputDropdownExpanded.value = false
                            outputUnit.value = "Inches"
                            oConversionFactor.doubleValue = 0.0254
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Yards") },
                        onClick = {
                            isOutputDropdownExpanded.value = false
                            outputUnit.value = "Yards"
                            oConversionFactor.doubleValue = 0.9144
                            convertUnits()
                        }
                    )
                }
            }
        }



        // Result section with top padding
        Spacer(modifier = Modifier.height(16.dp))
        val result = outputValue.value.toDoubleOrNull() // Safely converts to Double or returns null
        if (result != null && result != 0.0) {
            Text(
                text = "Result: ${outputValue.value} ${outputUnit.value}",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UnitConverter()
}
