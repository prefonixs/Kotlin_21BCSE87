package com.example.andbatch1

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
            Column (modifier = Modifier
                .padding(10.dp)
                .fillMaxSize()){
                AppNavigator()
            }
        }
    }

    @Composable
    fun AppNavigator(){
        val navController= rememberNavController()
        NavHost(navController = navController, startDestination = "Home"){
            composable("Home"){HomeScreen(navController)}
            composable("List/{name}/{age}"){backStackEntry -> ListScreen(navController,
                backStackEntry.arguments?.getString("name"),backStackEntry.arguments?.getString("age"))}
            composable("Grid/{name}/{age}"){backStackEntry -> GridScreen(navController,
                backStackEntry.arguments?.getString("name"),backStackEntry.arguments?.getString("age"))}
        }
    }
    @Composable
    fun HomeScreen(navController: NavController){
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()) {
            Text(text="Home Screen", fontSize = 30.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(100.dp))

            var name by remember { mutableStateOf("") }
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Enter your Name:") }
            )
            Spacer(modifier = Modifier.height(10.dp))

            var age by remember {mutableStateOf(0f)}
            Slider(
                value = age,
                onValueChange = { age = it },
                valueRange = 0f..100f,
                modifier = Modifier.width(300.dp)
            )
            Text(text = "age : ${round(age)}")
            Spacer(modifier = Modifier.height(100.dp))

            Button(onClick = {navController.navigate("List/$name/${round(age)}")}) {
                Text(text = "Go to List")
            }
        }
    }
    @Composable
    fun ListScreen(navController: NavController,name:String?="John",age:String?="18"){
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()) {
            Text(text="List Screen", fontSize = 30.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "Name: $name")
            Text(text = "Age: $age")
            Spacer(modifier = Modifier.height(20.dp))
            
            var itemsList=List(100){"items : $it"}
            LazyColumn(modifier = Modifier.height(500.dp).width(100.dp)
                            .padding(2.dp).border(2.dp, Color.Black)
            ){
                items(itemsList){
                    item->
                    BasicText(text = item)
                }
            }
            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = {navController.navigate("Grid/$name/$age")}) {
                Text(text = "Go to Grid")
            }
        }
    }
    @Composable
    fun GridScreen(navController: NavController,name:String?="John",age:String?="18"){
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()) {
            Text(text = "Grid Screen", fontSize = 30.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "Name: $name")
            Text(text = "Age: $age")
            Spacer(modifier = Modifier.height(20.dp))

            var itemsList = List(100) { "items : $it" }
            LazyVerticalGrid(
                columns = GridCells.Adaptive(100.dp),
                modifier = Modifier
                    .height(500.dp)
                    .padding(2.dp)
                    .border(2.dp, Color.Black)
            ) {
                items(itemsList.size) { index ->
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .border(1.dp, Color.Gray)
                            .padding(8.dp)
                    ) {
                        BasicText(text = itemsList[index])
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))

            Button(onClick = { navController.navigate("Home") }) {
                Text(text = "Go to Home")
            }
        }
    }
}
