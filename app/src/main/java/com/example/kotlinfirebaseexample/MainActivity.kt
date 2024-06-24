package com.example.kotlinfirebaseexample

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthMissingActivityForRecaptchaException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            LogInScreen()
            OTPScreen()
        }
    }

    val auth = FirebaseAuth.getInstance()
    var storedVerificationId: String? = null
    lateinit var resendToken: PhoneAuthProvider.ForceResendingToken

    val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            // This callback will be invoked in two situations:
            // 1 - Instant verification. In some cases the phone number can be instantly
            //     verified without needing to send or enter a verification code.
            // 2 - Auto-retrieval. On some devices Google Play services can automatically
            //     detect the incoming verification SMS and perform verification without
            //     user action.
            Log.d(TAG, "onVerificationCompleted:$credential")
            signINWithPhoneCred(credential)
        }

        override fun onVerificationFailed(e: FirebaseException) {
            // This callback is invoked in an invalid request for verification is made,
            // for instance if the the phone number format is not valid.
            Log.w(TAG, "onVerificationFailed", e)

            if (e is FirebaseAuthInvalidCredentialsException) {
                // Invalid request
            } else if (e is FirebaseTooManyRequestsException) {
                // The SMS quota for the project has been exceeded
            } else if (e is FirebaseAuthMissingActivityForRecaptchaException) {
                // reCAPTCHA verification attempted with null Activity
            }

            // Show a message and update the UI
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken,
        ) {
            // The SMS verification code has been sent to the provided phone number, we
            // now need to ask the user to enter the code and then construct a credential
            // by combining the code with a verification ID.
            Log.d(TAG, "onCodeSent:$verificationId")

            // Save verification ID and resending token so we can use them later
            storedVerificationId = verificationId
            resendToken = token
        }
    }

    fun startPhoneNumberVerification(phoneNumber: String) {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber) // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this) // Activity (for callback binding)
            .setCallbacks(callbacks) // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    fun verifyPhoneWithCode(code: String) {
        val credential = PhoneAuthProvider.getCredential(storedVerificationId!!, code)
        signINWithPhoneCred(credential)

    }

    fun signINWithPhoneCred(cred: PhoneAuthCredential) {
        auth.signInWithCredential(cred)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")

                    val user = task.result?.user
                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                    }
                    // Update UI
                }
            }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun OTPScreen() {
        val phoneNumber = remember { mutableStateOf("") }
        val otpCode = remember { mutableStateOf("") }
        var otpSent = remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(1, 164, 125)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(60.dp))
            Box(
                modifier = Modifier
                    .size(170.dp)
                    .background(Color(0, 135, 103), shape = RoundedCornerShape(100)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.phn_otp),
                    contentDescription = "phnOTP",
                    tint = Color.White,
                    modifier = Modifier
                        .size(120.dp)
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "OTP Verification", color = Color.White, fontSize = 30.sp)
            Spacer(modifier = Modifier.height(60.dp))

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Color.White,
                        shape = RoundedCornerShape(10, 10, 0, 0)
                    ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (!otpSent.value) {
                    TextField(
                        value = phoneNumber.value, onValueChange = {
                            phoneNumber.value = it
                        },
                        label = { Text(text = "Enter Phone Number") },
                        leadingIcon = {
                            Text(text = "+91")
                        },
                        colors = TextFieldDefaults.textFieldColors(containerColor = Color.White)
                    )

                    Spacer(modifier = Modifier.height(30.dp))
                    Button(
                        onClick = {
                            startPhoneNumberVerification("+91${phoneNumber.value}")
                            otpSent.value = true
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(86, 194, 168)),
                        shape = RoundedCornerShape(30),
                        modifier = Modifier.width(250.dp)
                    ) {
                        Text(text = "Send OTP", color = Color.White)
                    }
                } else {
                    TextField(
                        value = otpCode.value, onValueChange = {
                            otpCode.value = it
                        },
                        label = { Text(text = "Enter OTP") },
                        colors = TextFieldDefaults.textFieldColors(containerColor = Color.White)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = {
                            verifyPhoneWithCode(otpCode.value)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(86, 194, 168)),
                        shape = RoundedCornerShape(30),
                        modifier = Modifier.width(250.dp)
                    ) {
                        Text(text = "Verify OTP")
                    }
                }
            }
        }
    }

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
