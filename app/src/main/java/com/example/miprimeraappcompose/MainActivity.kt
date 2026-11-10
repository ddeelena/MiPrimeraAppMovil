package com.example.miprimeraappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.miprimeraappcompose.ui.theme.MiPrimeraAppComposeTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddReaction
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiPrimeraAppComposeTheme {
                val snackbarHostState = remember { SnackbarHostState() }
                val scope = rememberCoroutineScope()
                var nombre by remember { mutableStateOf("") }
                var nombreAMostrar by remember { mutableStateOf("") }
                var yearBirth by remember { mutableStateOf("") }
                var yearBirthAMostrar by remember { mutableStateOf("") }
                var actuallyYear by remember { mutableStateOf("") }
                var actuallyYearAMostrar by remember { mutableStateOf("") }
                var ageAMostrar by remember { mutableStateOf("") }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = { SnackbarHost(snackbarHostState) },
                    topBar = {
                        TopAppBar(
                            title = { Text("Datos Básicos") },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color.Yellow,
                                titleContentColor = Color.Black,
                                navigationIconContentColor = Color.Red,
                                actionIconContentColor = Color.DarkGray
                            ),
                            navigationIcon = {
                                IconButton(onClick = { }) {
                                    Icon(
                                        imageVector = Icons.Default.Menu,
                                        contentDescription = "Menú"
                                    )
                                }
                            }
                        )
                    },
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = {
                                nombreAMostrar = nombre
                                yearBirthAMostrar = yearBirth
                                actuallyYearAMostrar = actuallyYear
                                val birth = yearBirth.toIntOrNull() ?: 0
                                val current = actuallyYear.toIntOrNull() ?: 0
                                ageAMostrar = calcularEdad(birth, current).toString()

                                scope.launch {
                                    snackbarHostState.showSnackbar(
                                        message = "Hola $nombre, datos calculados correctamente",
                                    )
                                }
                            },
                            containerColor = Color.Yellow,
                            contentColor = Color.Red
                        ) {
                            Icon(
                                imageVector = Icons.Default.Calculate,
                                contentDescription = "Calcular datos"
                            )
                        }
                    }
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .padding(16.dp)
                            .fillMaxSize()
                    ) {
                        OutlinedTextField(
                            value = nombre,
                            onValueChange = { nombre = it },
                            label = { Text("Ingrese su nombre") },
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            value = yearBirth,
                            onValueChange = { yearBirth = it },
                            label = { Text("Ingrese su año de nacimiento") },
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            value = actuallyYear,
                            onValueChange = { actuallyYear = it },
                            label = { Text("Ingrese el año actual") },
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Hola\n\nNombre: $nombreAMostrar  \n Edad: $yearBirthAMostrar \nAño Actual: $actuallyYearAMostrar " +
                                    "\nEdad: $ageAMostrar años"
                        )
                    }
                }
            }
        }
    }
}

fun calcularEdad(yearBirth: Int, actuallyYear: Int): Int {
    return actuallyYear - yearBirth
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MiPrimeraAppComposeTheme {
        Greeting("Android")
    }
}
