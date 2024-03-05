 package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

 class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    Greeting("Android")
                    unitConvertor()
                }
            }
        }
    }
}

 @OptIn(ExperimentalMaterial3Api::class)
 @Composable
 fun unitConvertor (){

     var inputValue by remember{ mutableStateOf("") }
     var outputValue by remember{ mutableStateOf("0") }
     var inputUnit by remember{ mutableStateOf("Meters") }
     var outputUnit by remember{ mutableStateOf("Meters") }
     var iExpanded by remember{ mutableStateOf(false) }
     var oExpanded by remember{ mutableStateOf(false) }
     val conversionFactor = remember{ mutableStateOf(1.0) }
     val oConversionFactor = remember{ mutableStateOf(1.0) }

     fun convertUnit(){
         val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
//         val result = (inputValueDouble * conversionFactor.value*100/oConversionFactor.value).roundToInt()/100;
         val result = (inputValueDouble * conversionFactor.value *oConversionFactor.value * 100)/100;
         outputValue = result.toString()
     }

     Column (
         modifier = Modifier.fillMaxSize(),
         verticalArrangement = Arrangement.Center,
         horizontalAlignment = Alignment.CenterHorizontally
     ){
         //here items will appear in stack , one below another
         Text("Unit Convertor",
             style = MaterialTheme.typography.headlineLarge)
         Spacer(modifier = Modifier.height(16.dp))
         OutlinedTextField(
             value = inputValue,
             onValueChange ={
             inputValue = it
             //here we will write what to do when the text in the field changes
            },
             label = { Text(text = "Enter your value")})
         Spacer(modifier = Modifier.height(16.dp))
         Row {
             Box {
                 Button(onClick = { iExpanded = true }) {
                     Text(text = "Input Unit")
                     Icon(Icons.Default.ArrowDropDown , contentDescription = "Arrow down")
                 }
             }
             DropdownMenu(expanded = iExpanded , onDismissRequest = { iExpanded = false }) {
                 DropdownMenuItem(
                     text = { Text(text = "Centimeter") },
                     onClick = {
                         iExpanded = false
                         inputUnit = "Centimeter"
                         conversionFactor.value = 0.01
                         convertUnit()
                     })
                 DropdownMenuItem(
                     text = { Text(text = "Feet")},
                     onClick = {
                         iExpanded = false
                         inputUnit = "Feet"
                         conversionFactor.value = 0.3048
                         convertUnit()
                     }
                 )
                 DropdownMenuItem(
                     text = { Text(text = "Meter")},
                     onClick = {
                         iExpanded = false
                         inputUnit = "Meter"
                         conversionFactor.value = 1.0
                         convertUnit()
                     }
                 )
                 DropdownMenuItem(
                     text = { Text(text = "Millimeter")},
                     onClick = {
                         iExpanded = false
                         inputUnit = "Millimeter"
                         conversionFactor.value = 0.001
                         convertUnit()
                     }
                 )
             }
             Spacer(modifier = Modifier.width(16.dp))
             Box {
                 Button(onClick = { oExpanded = true}) {
                     Text(text = "Output Unit")
                     Icon(Icons.Default.ArrowDropDown , contentDescription = "Arrow down")
                 }

                 DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }) {
                     DropdownMenuItem(
                         text = { Text(text = "Centimeter") },
                         onClick = {
                             oExpanded = false
                             outputUnit = "Centimeter"
                             oConversionFactor.value = 100.0
                             convertUnit()
                         })
                     DropdownMenuItem(
                         text = { Text(text = "Feet")},
                         onClick = {
                             oExpanded = false
                             outputUnit = "Feet"
                             oConversionFactor.value = 3.2808399
                             convertUnit()
                         }
                     )
                     DropdownMenuItem(
                         text = { Text(text = "Meter")},
                         onClick = {
                             oExpanded = false
                             outputUnit = "Meter"
                             oConversionFactor.value = 1.0
                             convertUnit()
                         }
                     )
                 }
             }

         }
         Spacer(modifier = Modifier.height(16.dp))
         Text(text = "Result : ${outputValue} ${outputUnit}",
             style = MaterialTheme.typography.headlineMedium)
     }
 }

@Composable
@Preview(showBackground = true)
 fun unitConvertorPriview (){
     unitConvertor()
 }