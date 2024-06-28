package com.example.kotlinfirebaseexample

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.Firebase
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthMissingActivityForRecaptchaException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import com.google.firebase.storage.storage
import java.util.UUID
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val userList = remember { mutableStateOf<List<Firebaseuser>>(emptyList()) }
            LaunchedEffect(Unit) {
                fetchFirebaseUsers { users ->
                    userList.value = users
                }
            }
            val context = LocalContext.current
            val permissionLauncher =
                rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) {
                    if (it) {
                        Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Please Denied", Toast.LENGTH_SHORT).show()
                    }
                }
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(600.dp)
                ) {
                    WebViewScreen(url = "https://silicon.ac.in")
                }
                LinkText("Got to full website","https://silicon.ac.in")
                SharedPrefExample(context = context)
            }

            Column {
////                LogInScreen()
////                OTPScreen()
//                Text(
//                    text = "User Screen",
//                    fontSize = 30.sp,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(10.dp),
//                    textAlign = TextAlign.Center
//                )
//                AddUserScreen(userList.value) {
//                    fetchFirebaseUsers { users ->
//                        userList.value = users
//                    }
//                }
//                Text(
//                    text = "User List",
//                    fontSize = 20.sp,
//                    modifier = Modifier
//                        .padding(10.dp),
//                )
//                LazyColumn(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(10.dp)
//                ) {
//                    items(userList.value) { user ->
//                        var updateAge by remember { mutableStateOf(false) }
//                        var newAge by remember { mutableStateOf(user.age.toString())}
//                        val context = LocalContext.current
//                        val launcher =
//                            rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
//                                uri?.let {
//                                    uploadImage(it, context, user.name) {
//                                        fetchFirebaseUsers { users ->
//                                            userList.value = users
//                                        }
//                                    }
//                                }
//                            }
//                        Row(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(8.dp),
//                            verticalAlignment = Alignment.CenterVertically,
//                            horizontalArrangement = Arrangement.SpaceBetween
//                        ) {
//                            Column {
//                                Text(text = "Pfp:", color = Color.LightGray)
//                                if (user.profilePictureUrl == null) {
//                                    TextButton(onClick = {
//                                        launcher.launch("image/*")
//                                    }) {
//                                        Icon(
//                                            imageVector = Icons.Default.AddCircle,
//                                            contentDescription = ""
//                                        )
//                                    }
//                                } else {
//                                    Image(
//                                        painter = rememberAsyncImagePainter(user.profilePictureUrl),
//                                        contentDescription = "Profile Picture",
//                                        modifier = Modifier.size(50.dp)
//                                    )
//                                }
//
//                            }
//                            Column {
//                                Text(text = "Name:", color = Color.LightGray)
//                                Text(text = user.name)
//                            }
//                            Column {
//                                Text(text = "Age:", color = Color.LightGray)
//                                Row {
//                                    if (!updateAge) {
//                                        Text(text = user.age.toString())
//                                        TextButton(
//                                            onClick = { updateAge = true },
//                                            contentPadding = PaddingValues(0.dp),
//                                            modifier = Modifier.height(20.dp)
//                                        ) {
//                                            Icon(
//                                                imageVector = Icons.Default.Edit,
//                                                contentDescription = "",
//                                                modifier = Modifier
//                                                    .size(15.dp)
//                                                    .padding(0.dp)
//                                            )
//                                        }
//                                    } else {
//                                        TextField(value = newAge, onValueChange = {
//                                            newAge = it
//                                        }, modifier = Modifier
//                                            .width(100.dp)
//                                            .height(50.dp),
//                                            trailingIcon = {
//                                                TextButton(
//                                                    onClick = {
//                                                        updateUserAgeInFirebaseDB(
//                                                            user.name,
//                                                            newAge.toInt()
//                                                        ) {
//                                                            fetchFirebaseUsers { users ->
//                                                                userList.value = users
//                                                            }
//                                                        }
//                                                        updateAge = false
//                                                    }, contentPadding = PaddingValues(0.dp),
//                                                    modifier = Modifier.height(20.dp)
//                                                ) {
//                                                    Icon(
//                                                        imageVector = Icons.Default.CheckCircle,
//                                                        contentDescription = "",
//                                                        modifier = Modifier
//                                                            .size(15.dp)
//                                                            .padding(0.dp)
//                                                    )
//                                                }
//                                            })
//                                    }
//                                }
//                            }
//                            Button(onClick = {
//                                deleteUserFromFirebaseDB(user.name) {
//                                    fetchFirebaseUsers { users ->
//                                        userList.value = users
//                                    }
//                                }
//                            }) {
//                                Icon(imageVector = Icons.Default.Delete, contentDescription = "")
//                                Text(text = "Delete")
//                            }
//                        }
//                        HorizontalDivider()
//                    }
//                }
//                Spacer(modifier = Modifier.height(20.dp))
//                Button(onClick = {
//                    permissionLauncher.launch(android.Manifest.permission.CAMERA)
//                }) {
//                    Text(text = "Grant camera access")
//                }
//                Spacer(modifier = Modifier.height(20.dp))
//                Button(onClick = {
//                    permissionLauncher.launch(android.Manifest.permission.ACCESS_BACKGROUND_LOCATION)
//                }) {
//                    Text(text = "Grant location access")
//                }
//                Spacer(modifier = Modifier.height(20.dp))
//                Button(onClick = {
//                    permissionLauncher.launch(android.Manifest.permission.ACCESS_MEDIA_LOCATION)
//                }) {
//                    Text(text = "Grant media access")
//                }
////                ImageUploadScreen()
            }
        }
    }

    val auth = FirebaseAuth.getInstance()
    val firebaseDB = Firebase.firestore
    var storedVerificationId: String? = null
    lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    val storage = Firebase.storage
    val storageRef = storage.reference
    fun uploadImage(uri: Uri, context: Context, userName: String, onResult: () -> Unit) {
        val fileName = "userPfps/${UUID.randomUUID()}.jpg"
        val imageRef = storageRef.child(fileName)

        imageRef.putFile(uri)
            .addOnCompleteListener { takeSnapShot ->
                imageRef.downloadUrl.addOnSuccessListener { uri ->
                    Toast.makeText(
                        context,
                        "Image Uploaded Successfully: ${uri}",
                        Toast.LENGTH_SHORT
                    ).show()
                    updateProfilePictureUrl(userName, uri.toString()) {
                        onResult()
                    }
                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(
                    context,
                    "Image Upload failed: ${exception.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

    fun addUserToFirebaseDB(name: String, age: Int, onResult: () -> Unit) {
        val isAdult = age >= 18
        val firebaseUser = Firebaseuser(name, age, isAdult)
        firebaseDB.collection("Users")
            .add(firebaseUser)
            .addOnSuccessListener { dRef ->
                Log.d(TAG, "Document added with ID: ${dRef.id}")
                onResult()
            }
            .addOnFailureListener { e ->
                Log.d(TAG, "Document couldn't be added: ${e.message}")
            }
    }

    fun fetchFirebaseUsers(onResult: (List<Firebaseuser>) -> Unit) {
        firebaseDB.collection("Users")
            .get()
            .addOnSuccessListener { result ->
                val userList = result.map { document ->
                    document.toObject(Firebaseuser::class.java)
                }
                onResult(userList)
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error Getting data", e)
            }
    }

    fun deleteUserFromFirebaseDB(name: String, onResult: () -> Unit) {
        firebaseDB.collection("Users")
            .whereEqualTo("name", name)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    firebaseDB.collection("Users").document(document.id).delete()
                        .addOnSuccessListener {
                            Log.d(TAG, "DocumentSnapshot successfully deleted!")
                            onResult()
                        }
                        .addOnFailureListener { e ->
                            Log.w(TAG, "Error deleting document", e)
                        }
                }
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error getting documents", e)
            }
    }

    fun updateUserAgeInFirebaseDB(name: String, newAge: Int, onResult: () -> Unit) {
        firebaseDB.collection("Users")
            .whereEqualTo("name", name)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val docRef = firebaseDB.collection("Users").document(document.id)
                    docRef.update("age", newAge, "adult", newAge >= 18)
                        .addOnSuccessListener {
                            Log.d(TAG, "DocumentSnapshot successfully updated!")
                            onResult()
                        }
                        .addOnFailureListener { e ->
                            Log.w(TAG, "Error updating document", e)
                        }
                }
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error getting documents", e)
            }
    }

    fun updateProfilePictureUrl(name: String, url: String, onResult: () -> Unit) {
        firebaseDB.collection("Users")
            .whereEqualTo("name", name)
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val docRef = firebaseDB.collection("Users").document(document.id)
                    docRef.update("profilePictureUrl", url)
                        .addOnSuccessListener {
                            Log.d(TAG, "DocumentSnapshot successfully updated!")
                            onResult()
                        }
                        .addOnFailureListener { e ->
                            Log.w(TAG, "Error updating document", e)
                        }
                }
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error getting documents", e)
            }
    }


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

//    @Composable
//    fun ImageUploadScreen(){
//        val context= LocalContext.current
//        val imageUri= remember {
//            mutableStateOf<Uri?>(null)
//        }
//        val launcher= rememberLauncherForActivityResult(ActivityResultContracts.GetContent()){uri:Uri?->
//            imageUri.value=uri
//            uri?.let{
//                uploadImage(it, context)
//            }
//        }
//        Column {
//            Button(onClick = {
//                launcher.launch("image/*")
//            }) {
//                Text(text = "Select Image")
//            }
//            Spacer(modifier = Modifier.height(10.dp))
//            imageUri.value.let{uri->
//                Image(painter = rememberAsyncImagePainter(uri), contentDescription = "Upload Image", modifier = Modifier.size(250.dp))
//            }
//        }
//    }


    @Composable
    fun WebViewScreen(url: String) {
        val context = LocalContext.current
        AndroidView(
            factory = {
                WebView(context).apply {
                    webViewClient = WebViewClient()
//                    settings.javaScriptEnabled = true
                    loadUrl(url)
                }
            },
            update = { webView ->
                webView.loadUrl(url)

            },
            modifier = Modifier.fillMaxSize()
        )
    }

    @Composable
    fun SharedPrefExample(context: Context) {
        val sharedPref = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        var text by remember {
            mutableStateOf(sharedPref.getString("saved_text", "") ?: "")
        }
        Column(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(value = text, onValueChange = {
                text = it
            }, label = { Text(text = "Enter some text") })
            Button(onClick = {
                editor.putString("saved_text", text)
                editor.apply()
            }) {
                Text(text = "Save to Shared Pref")
            }
            Text(text = "Saved Text: ${sharedPref.getString("saved_text", "")}")
        }
    }

    @Composable
    fun LinkText(text: String, url: String) {
        val context = LocalContext.current
        val annotatedString = buildAnnotatedString {
            append(text)
            addStyle(
                style =  SpanStyle(
                    color = Color.Blue,
                    textDecoration = TextDecoration.Underline
                ),
                start = 0,
                end = text.length
            )
            addStringAnnotation(
                tag = "URL",
                annotation = url,
                start = 0,
                end = url.length
            )
        }

        ClickableText(text = annotatedString,
            onClick = { offset ->
                annotatedString.getStringAnnotations("URL", offset, offset)
                    .firstOrNull()?.let {stringAnnotation->
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(stringAnnotation.item))
                        context.startActivity(intent)
                    }
            }
        )
    }

    @Composable
    fun AddUserScreen(userList: List<Firebaseuser>, refreshUserList: () -> Unit) {
        val name = remember { mutableStateOf("") }
        val age = remember { mutableStateOf("") }
        Column {
            Text(
                text = "Add User",
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(10.dp),
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(value = name.value, onValueChange = {
                    name.value = it
                }, label = {
                    Text(text = "Enter Name")
                }, modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(10.dp))
                TextField(value = age.value, onValueChange = {
                    age.value = it
                }, label = {
                    Text(text = "Enter Age")
                }, modifier = Modifier.fillMaxWidth()
                )
                Row {
                    Button(onClick = {
                        addUserToFirebaseDB(name.value, age.value.toInt()) {
                            refreshUserList()
                        }
                    }) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = "")
                        Text(text = "Add User")
                    }
//                    Spacer(modifier = Modifier.width(10.dp))
//                    Button(onClick = { signIN(email.value, password.value) }) {
//                        Text(text = "Login")
//                    }
                }
//                if (showDialog.value) {
//                    AlertUser(
//                        email = alertMessage.value,
//                        newUser = isNewUser.value,
//                        onDismiss = { showDialog.value = false }
//                    )
//                }
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

data class Firebaseuser(
    val name: String = "",
    val age: Int = 0,
    val isAdult: Boolean = false,
    val profilePictureUrl: String? = null
)
