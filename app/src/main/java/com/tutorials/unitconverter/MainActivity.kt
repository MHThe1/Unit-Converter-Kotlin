package com.tutorials.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.tutorials.unitconverter.ui.theme.UnitConverterTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.platform.LocalContext
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
    var inputUnit = remember { mutableStateOf("Centimeters") }
    var outputUnit = remember { mutableStateOf("Feet") }
    var isInputDropdownExpanded = remember { mutableStateOf(false) }
    var isOutputDropdownExpanded = remember { mutableStateOf(false) }
    val conversionFactor = remember { mutableDoubleStateOf(0.01) }

    val context = LocalContext.current

    fun convertUnits() {
        val inputValueDouble = inputValue.value.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversionFactor.doubleValue * 100.0).roundToInt() / 100.0
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
            },
            label = { Text("Enter Value") }
        )

        Row {
            // First Dropdown
            Box {
                Button(onClick = { isInputDropdownExpanded.value = !isInputDropdownExpanded.value }) {
                    Text(text = if(inputUnit.value == ""){
                        "Select"
                    }else{
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
                }
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Second Dropdown
            Box {
                Button(onClick = { isOutputDropdownExpanded.value = true }) {
                    Text(text = "Select")
                    Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                }
                DropdownMenu(
                    expanded = isOutputDropdownExpanded.value,
                    onDismissRequest = { isOutputDropdownExpanded.value = false }
                ) {
                    DropdownMenuItem(
                        text = { Text("Kilograms") },
                        onClick = { /* TODO: Handle selection */ }
                    )
                    DropdownMenuItem(
                        text = { Text("Pounds") },
                        onClick = { /* TODO: Handle selection */ }
                    )
                    DropdownMenuItem(
                        text = { Text("Grams") },
                        onClick = { /* TODO: Handle selection */ }
                    )
                }
            }
        }

        // Button with Toast(popup feedback)
        Button(
            onClick = {
                Toast.makeText(
                    context,
                    "Thanks for clicking!",
                    Toast.LENGTH_LONG
                ).show()
            },
        ) {
            Text(text = "Convert")
        }

        // Result section with top padding
        Spacer(modifier = Modifier.height(16.dp))
        Text("Result")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UnitConverter()
}
