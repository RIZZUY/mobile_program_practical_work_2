package com.example.practical_2

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.practical_2.ui.theme.Practical_2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Practical_2Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "1-st Activity",
            fontSize = 24.sp,
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        Button(
            onClick = {
                val intent = Intent(context, SecondActivity::class.java)
                context.startActivity(intent)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Text("Go to 2-nd")
        }

        Button(
            onClick = { showDialog = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Dialog")
        }
    }

    if (showDialog) {
        val members = listOf("Jānis Bērziņš", "Anna Kalniņa", "Pēteris Ozoliņš")
        val checkedStates = remember { mutableStateMapOf<String, Boolean>().apply { members.forEach { this[it] = false } } }

        AlertDialog(
            onDismissRequest = {},
            title = { Text("1. Group's Dialog") },
            text = {
                Column {
                    members.forEach { name ->
                        val isChecked = checkedStates[name] == true
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = isChecked,
                                onCheckedChange = { checked ->
                                    checkedStates[name] = checked
                                    if (checked) {
                                        Toast.makeText(context, "$name checked", Toast.LENGTH_SHORT).show()
                                    } else {
                                        Toast.makeText(context, "$name unchecked", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = name)
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        Toast.makeText(context, "You clicked OK", Toast.LENGTH_SHORT).show()
                    }
                ) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        Toast.makeText(context, "You closed dialog", Toast.LENGTH_SHORT).show()
                        showDialog = false
                    }
                ) {
                    Text("Close")
                }
            }
        )
    }
}