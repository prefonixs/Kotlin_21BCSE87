package com.example.kotlinfirebaseexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LogInScreen()
        }
    }

    val auth = FirebaseAuth.getInstance()

    @Composable
    fun LogInScreen() {
        val email = remember {
            mutableStateOf("")
        }
        val password = remember {
            mutableStateOf("")
        }
        val showDialog = remember {
            mutableStateOf(false)
        }
        val alertMessage = remember {
            mutableStateOf("")
        }
        val isNewUser = remember {
            mutableStateOf(false)
        }

        fun signUP(email: String, password: String) {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        println(user)
                        alertMessage.value = "${user?.email}"
                        isNewUser.value = true
                        showDialog.value = true
                    } else {
                        println("User couldn't be created")
                        println(task.exception?.message)
                    }
                }
        }

        fun signIN(email: String, password: String) {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        println("User Logged in $user")
                        alertMessage.value = "${user?.email}"
                        isNewUser.value = false
                        showDialog.value = true
                    } else {
                        println("User couldn't log in")
                        println(task.exception?.message)
                    }
                }
        }

        Column {
            Text(
                text = "Login Screen",
                fontSize = 30.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                textAlign = TextAlign.Center
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(value = email.value, onValueChange = {
                    email.value = it
                }, label = {
                    Text(text = "Enter Email")
                }, modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(10.dp))
                TextField(value = password.value, onValueChange = {
                    password.value = it
                }, label = {
                    Text(text = "Enter Email")
                }, modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation()
                )
                Row {
                    Button(onClick = {
                        signUP(email.value, password.value)
                    }) {
                        Text(text = "Register")
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Button(onClick = { signIN(email.value, password.value) }) {
                        Text(text = "Login")
                    }
                }
                if (showDialog.value) {
                    AlertUser(
                        email = alertMessage.value,
                        newUser = isNewUser.value,
                        onDismiss = { showDialog.value = false }
                    )
                }
            }
        }
    }
    @Composable
    fun AlertUser(email: String, newUser: Boolean, onDismiss: () -> Unit) {
        AlertDialog(
            onDismissRequest = onDismiss,
            confirmButton = { TextButton(onClick = onDismiss) { Text("OK") } },
            title = {
                Text(
                    text = if (newUser) "Hello new User" else "Welcome back User"
                )
            },
            text = {
                Text(text = "Your email is: $email")
            }
        )
    }
}
