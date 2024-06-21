package com.example.andbatch1

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AssistChip
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.andbatch1.ui.theme.AndBatch1Theme
import java.lang.Math.round

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalLayoutApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val gColors = listOf(Color.Red, Color.Blue, Color.Green)
        setContent {
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxSize(),
//                horizontalAlignment = Alignment.CenterHorizontally,
//                verticalArrangement = Arrangement.Center
            ) {
//                CounterView()
                LoginScreen()
            }
        }
    }

    @Composable
    fun CounterView(counterVM: CounterViewModel = viewModel()) {
        val counterState = counterVM.counter.value
        val intBox = GenericBox(10)
        val StringBox = GenericBox("Hello World!")
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Counter Value : ${counterState.count}", textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(10.dp))
            Row {
                Button(onClick = { counterVM.incrementCounter() }) {
                    Text(text = "+")
                }
                Spacer(modifier = Modifier.width(10.dp))
                Button(onClick = { counterVM.resetCounter() }) {
                    Text(text = "Reset")
                }
                Spacer(modifier = Modifier.width(10.dp))
                Button(onClick = { counterVM.decrementCounter() }) {
                    Text(text = "-")
                }
            }
            Spacer(modifier = Modifier.height(100.dp))
            Text(text = "Int Generic class value : ${intBox.value}")
            Text(text = "String Generic class value : ${StringBox.value}")
        }
    }
}

data class Counter(val count: Int)
class CounterViewModel : ViewModel() {
    private val _counter = mutableStateOf(Counter(0))
    val counter: State<Counter> = _counter

    fun incrementCounter() {
        _counter.value = Counter(_counter.value.count + 1)
    }

    fun decrementCounter() {
        _counter.value = Counter(_counter.value.count - 1)
    }

    fun resetCounter() {
        _counter.value = Counter(0)
    }
}

class GenericBox<T>(temp: T) {
    var value = temp
}

@Composable
fun LoginScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    Card(colors = CardDefaults.cardColors(containerColor = Color(239,244,250)),
        modifier = Modifier.padding(15.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
        Column(modifier = Modifier.padding(15.dp)) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Jetpack Compose",
                    style = TextStyle(fontSize = 27.sp, color = Color(2,100,157)),
                )
                Spacer(modifier = Modifier.height(16.dp))
                Image(
                    painter = painterResource(id = R.drawable.jetpack_compose_icon),
                    contentDescription = "Jetpack Compose Logo",
                    modifier = Modifier.size(100.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Login",
                style = TextStyle(fontSize = 30.sp, color = Color(10,106,67))
            )
            Spacer(modifier = Modifier.height(24.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(text = "Email ID or Mobile Number") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(25.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "Password") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                        val visibilityIcon =
                            if (passwordVisibility) Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff
                        Icon(imageVector = visibilityIcon, contentDescription = "Toggle visibility")
                    }
                }
            )
            Spacer(modifier = Modifier.height(4.dp))
            TextButton(onClick = { /* TODO*/ }, modifier = Modifier.align(Alignment.End)) {
                Text(text = "Forgot Password?", color = Color(51,128,95))
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { /* TODO*/ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0,99,154)),
//                modifier = Modifier.width(120.dp)
            ) {
                Text(text = "Login", fontSize = 15.sp, modifier = Modifier.padding(10.dp))
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
    TextButton(onClick = { /* TODO*/ }) {
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Don't have an account? ", color = Color.DarkGray)
            Text(text = "Register", color = Color(68,121,162))
        }
    }
}