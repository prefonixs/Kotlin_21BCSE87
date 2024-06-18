package com.example.andbatch1

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AssistChip
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import java.lang.Math.round

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalLayoutApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val gColors = listOf(Color.Red, Color.Blue, Color.Green)
        setContent {
            ScaffoldExample()
        }
    }
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ScaffoldExample(){
        Scaffold(
            topBar = {
                TopAppBar(colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ), title = { Text(text = "Top bar", fontSize = 30.sp) })
            },
            bottomBar = {
                BottomAppBar(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    contentColor = MaterialTheme.colorScheme.primary
                ) {
                    Text(text = "Bottom bar")
                }
            },
            floatingActionButton = {
                FloatingActionButton(onClick = { println("Edit is clicked") }) {
                    Icon(Icons.Default.Edit, contentDescription = "Edit button")
                }
            }
        ) {
            Column(modifier = Modifier.padding(it)) {
                var showChip by remember {mutableStateOf(true)}
                if (showChip) {
                    AssistChip(
                        modifier = Modifier.padding(10.dp),
                        onClick = { showChip = false },
                        label = { Text(text = "Chip") },
                        leadingIcon = {
                            Icon(
                                Icons.Default.Close,
                                contentDescription = "close"
                            )
                        })
                }
                Card (colors = CardDefaults.cardColors(containerColor = Color.Green), modifier = Modifier
                    .size(width = 250.dp, height = 200.dp)
                    .padding(10.dp)){
                    Text(
                        text = "Card Title",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 25.sp,
                        modifier = Modifier.padding(15.dp),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Name : Sidharth Mishra",
                        fontSize = 20.sp,
                        modifier = Modifier.padding(10.dp),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "SIC: 21BCSE87",
                        fontSize = 20.sp,
                        modifier = Modifier.padding(10.dp),
                        textAlign = TextAlign.Center
                    )
                }
                var sliderVal by remember {mutableStateOf(0f)}
                Slider(
                    modifier = Modifier.padding(10.dp),
                    value = sliderVal,
                    onValueChange = { sliderVal = it },
                    steps=9,
                    valueRange = 0f..100f
                )
                Text(text = "value : ${round(sliderVal)}",modifier = Modifier.padding(10.dp))
                Divider(thickness = 3.dp, color = Color.Gray)
                CircularProgressIndicator(modifier = Modifier.padding(10.dp))
                var checked by remember {
                    mutableStateOf(true)
                }
                Switch(checked = checked, onCheckedChange = {checked=it},modifier = Modifier.padding(10.dp))
                var showBottomSheet by remember {mutableStateOf(false)}
                Button(onClick = {showBottomSheet=true}) {
                    Text(text = "Show BottomSheet")
                }
                if(showBottomSheet){
                    ModalBottomSheet(onDismissRequest = {showBottomSheet=false},modifier = Modifier.fillMaxSize()) {
                        Text(text = "Bottom Sheet",
                            fontSize = 20.sp,
                            modifier = Modifier.padding(10.dp),
                            textAlign = TextAlign.Center)
                    }
                }
            }
        }
    }
}
