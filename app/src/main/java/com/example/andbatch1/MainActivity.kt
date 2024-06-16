package com.example.andbatch1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val gColors = listOf(Color.Red, Color.Blue, Color.Green)
        setContent {
            Column(modifier = Modifier.fillMaxSize().padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(50.dp)) {
                Text(
                    text = "Text properties: text decoration, fontFamily and letter spacing",
                    fontFamily = FontFamily.Serif,
                    fontSize = 20.sp,
                    textDecoration = TextDecoration.Underline,
                    letterSpacing = 5.sp
                )
                Text(
                    text = "Text properties: fontWeight, fontSize and fontColor",
                    color = Color.Magenta,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 25.sp
                )
                Text(
                    text = "Text properties: text alignment and gradient of red, blue, green",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.background(
                        brush = Brush.linearGradient(
                            colors = gColors
                        )
                    ),
                    color = Color.White,
                    fontSize = 20.sp
                )
                Text(
                    text = "Text properties: overflow clip, Lorem ipsum dolor sit amet, consectetur adipiscing elit",
                    overflow = TextOverflow.Clip,
                    softWrap = false
                )
                Text(
                    text = "Text properties: overflow ellipsis, Lorem ipsum dolor sit amet, consectetur adipiscing elit",
                    overflow = TextOverflow.Ellipsis,
                    softWrap = false
                )
                Text(
                    text = "Text properties: overflow visible, Lorem ipsum dolor sit amet, consectetur adipiscing elit",
                    overflow = TextOverflow.Visible,
                    softWrap = false
                )
                Text(
                    text = "Text properties: line height, Lorem ipsum dolor sit amet, consectetur adipiscing elit",
                    lineHeight = 30.sp
                )
            }
//            AndBatch1Theme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Greeting("Sid")
//                }
//            }
        }
    }
}

//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    AndBatch1Theme {
//        Greeting("Android")
//    }
//}