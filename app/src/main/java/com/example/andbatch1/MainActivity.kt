package com.example.andbatch1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.text.style.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.example.andbatch1.ui.theme.AndBatch1Theme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalLayoutApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val gColors = listOf(Color.Red, Color.Blue, Color.Green)
        setContent {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)) {

                Text(text = "Layouts", fontWeight = FontWeight.Bold, fontSize = 25.sp)
                Column() {
                    Text("column Item 1")
                    Text("column Item 2")
                    Text("column Item 3")
                }
                Row() {
                    Text("row Item 1")
                    Text("row Item 2")
                    Text("row Item 3")
                }
                Box() {
                    Text("box Item Hello")
                    Text("box Item World")
                    Text("box Item Sidharth")
                }

                Text(text = "Buttons", fontWeight = FontWeight.Bold, fontSize = 25.sp)
                FlowRow( modifier = Modifier.width(1000.dp), horizontalArrangement = Arrangement.Center) {
                    Button(onClick = { /*TODO*/ },modifier = Modifier.padding(8.dp)) {
                        Text("Filled")
                    }
                    FilledTonalButton(onClick = { /*TODO*/ },modifier = Modifier.padding(8.dp)) {
                        Text("Tonal")
                    }
                    OutlinedButton(onClick = { /*TODO*/ },modifier = Modifier.padding(8.dp)) {
                        Text("Outlined")
                    }
                    ElevatedButton(onClick = { /*TODO*/ },modifier = Modifier.padding(8.dp)) {
                        Text("Elevated")
                    }
                    TextButton(onClick = { /*TODO*/ },modifier = Modifier.padding(8.dp)){
                        Text("Text Button")
                    }
                }

                Text(text = "Dialog", fontWeight = FontWeight.Bold, fontSize = 25.sp)
                var showDialog = remember { mutableStateOf(false) }

                if (showDialog.value) {
                    AlertDialog(
                        onDismissRequest = { showDialog.value = false },
                        title = { Text(text = "Title") },
                        text = { Text("This is a dialog") },
                        confirmButton = {
                            Button(onClick = { showDialog.value = false }) {
                                Text("OK")
                            }
                        }
                    )
                }

                Button(onClick = { showDialog.value = true }) {
                    Text("Show Dialog")
                }
            }
        }
    }
}